package com.computer.community_laundry.service.laundry;

import com.computer.community_laundry.mapper.laundry.*;
import com.computer.community_laundry.support.entity.laundry.*;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.computer.community_laundry.support.utils.OrderUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: 商家端订单业务实现
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    final LaundryOrderMapper orderMapper;

    final LaundryClothesMapper clothesMapper;

    final LaundryEquipmentMapper equipmentMapper;

    final WorkingprocedureMapper workingprocedureMapper;

    final TechnologicalprocessMapper technologicalprocessMapper;

    @Qualifier("orderUtils")
    @Autowired
    public OrderUtils orderUtils;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


    /**
     * 依据工序、流程、设备状态，可同洗的衣物等分配洗衣任务
     * 先查出未使用的设备（此处未使用包含已有衣服放入 但是未达到容量 因为商家需要达到洗衣机容量才开始洗 为了节省资源 达到设备
     * 最大利用率），然后根据当前订单设定工序查出工序相同的所有要洗的衣服，在根据当前订单设定流程查出流程相同的所有要
     * 洗的衣服。再根据衣服的颜色进行分类分配设备
     * @param laundryOrder
     * @return
     */
    @Transactional
    public ResponseData upLaundryService(LaundryOrder laundryOrder){
        int result = 0;
        // 依据工序 当前订单衣服设定工序相同的衣服查询
        List<LaundryClothes> workClothes = orderUtils.getWorkClothes(laundryOrder.getWorkingprocedure(),laundryOrder.getOrdernum());
        // 依据流程 当前订单衣服设定流程相同的衣服查询
        List<LaundryClothes> technologicalClothes = orderUtils.getTechnologicalClothess(laundryOrder.getTechnologicalprocess(),laundryOrder.getOrdernum());
        /**
         * 判断当前订单衣服设定工序相同的衣服信息和当前订单衣服设定流程相同的衣服信息 如果有数据
         * 拿出来过滤这些衣服所分配的设备是否已经开始洗 如果没有数据  那就说明当前订单中衣服设定的工序和流程没有相同的衣服
         * 直接将订单中的衣服 进行 品种和颜色分类放到新的未使用的设备中
         */
        if(workClothes.size() > 0 && technologicalClothes.size() > 0){

            // 上面已经查出工序、流程、设备状态同当前订单中衣服相同的所有衣物 下面筛选衣服种类和颜色 将颜色相同 种类相同的放到同一个设备洗，此处考虑设备容量
            // 查询当前订单衣服信息 并与已过滤好的待洗衣服进行 颜色 种类 分类
            List<LaundryClothes> laundryClothes = clothesMapper.queryColthesByNum(laundryOrder.getOrdernum());
            for (LaundryClothes clothes: laundryClothes) {
                // 每次进入都需重新计算空空闲设备和带洗衣服
                List<LaundryEquipment> laundryEquipments = orderUtils.getLaundryEquipments();
                List<LaundryClothes> lastClothesList = orderUtils.getLastClothesList(workClothes, technologicalClothes, laundryEquipments);
                String clname = clothes.getClname();
                String color = clothes.getColor();
                for (LaundryClothes lastClothes: lastClothesList) {
                    // 衣服种类和颜色相同的放到一起
                    if(clname.equals(lastClothes.getClname()) && color.equals(lastClothes.getColor())){
                        //现在是颜色种类工序流程状态都一样了  需要查看洗衣机是否已满
                        //查询设备容量
                        Map<String, Integer> capacityMap = orderUtils.getCapacity(lastClothes.getEquipmentid());
                        // 设备容量
                        Integer capacity = capacityMap.get("capacity");
                        // 已占用容量
                        Integer clocount = capacityMap.get("clocount");
                        if(capacity - clocount > 1){
                            clothes.setEquipmentid(lastClothes.getEquipmentid());
                            result = clothesMapper.updateByPrimaryKeySelective(clothes);
                            break;
                        }else {
                            List<LaundryEquipment> equipmentsCollect = orderUtils.getEquipmentsCollect();
                            for (LaundryEquipment laundryEquipment : equipmentsCollect) {
                                if(clname.equals(laundryEquipment.getWashingobjec())){
                                    clothes.setEquipmentid(laundryEquipment.getId());
                                    result = clothesMapper.updateByPrimaryKeySelective(clothes);
                                    break;
                                }
                            }
                            break;
                        }
                    }else{
                        List<LaundryEquipment> equipmentsCollect = orderUtils.getEquipmentsCollect();
                        for (LaundryEquipment laundryEquipment : equipmentsCollect) {
                            if(clname.equals(laundryEquipment.getWashingobjec())){
                                clothes.setEquipmentid(laundryEquipment.getId());
                                result = clothesMapper.updateByPrimaryKeySelective(clothes);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            LaundryOrder upOrder = new LaundryOrder();
            upOrder.setId(laundryOrder.getId());
            upOrder.setReservationstatus("5");
            result = orderMapper.updateByPrimaryKeySelective(upOrder);
        }else{
            // 查询当前订单衣服信息 进行 颜色 种类 分类放到洗衣设备中
            List<LaundryClothes> laundryClothes = clothesMapper.queryColthesByNum(laundryOrder.getOrdernum());
            for (LaundryClothes clothes: laundryClothes) {
                //衣服品种
                String clname = clothes.getClname();
                //查询完全未使用设备
                List<LaundryEquipment> equipmentsCollect = orderUtils.getEquipmentsCollect();
                for (LaundryEquipment laundryEquipment : equipmentsCollect) {
                    if(clname.equals(laundryEquipment.getWashingobjec())){
                        clothes.setEquipmentid(laundryEquipment.getId());
                        result = clothesMapper.updateByPrimaryKeySelective(clothes);
                        break;
                    }
                }
            }
            LaundryOrder upOrder = new LaundryOrder();
            upOrder.setId(laundryOrder.getId());
            upOrder.setReservationstatus("5");
            result = orderMapper.updateByPrimaryKeySelective(upOrder);
        }
        // 检查设备状态 设备容量已满  改为使用中
        List<LaundryEquipment> laundryEquipments = equipmentMapper.queryEquipmentCapacity();
        for (LaundryEquipment laundryEquipment : laundryEquipments) {
            String capacity = laundryEquipment.getCapacity();
            Integer clothescount = laundryEquipment.getClothescount();
            if(Integer.valueOf(capacity) - clothescount == 0){
                LaundryEquipment equipment = new LaundryEquipment();
                equipment.setId(laundryEquipment.getId());
                equipment.setStatus("2");
                result = equipmentMapper.updateByPrimaryKeySelective(equipment);
            }
        }
        return result > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("任务分配失败");
    }

    /**
     * 分页条件查询订单信息（商家端）
     * @param page
     * @param limit
     * @param phone
     * @return
     */
    public LayUIPage queryOrderPaging(int page,int limit,String status,String phone){
        PageHelper.startPage(page,limit);
        List<LaundryOrder> laundryOrders = orderMapper.queryOrderPaging(phone,status);
        PageInfo pageInfo = new PageInfo(laundryOrders);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 根据订单编号查询当前订单下衣服信息
     * @param ordernum
     * @return
     */
    public ResponseData queryColthesByNum(String ordernum){
        List<LaundryClothes> laundryClothes = clothesMapper.queryColthesByNum(ordernum);
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(laundryClothes);
    }

    /**
     * 根据订单编号查询当前订单下衣服信息及分配设备信息
     * @param ordernum
     * @return
     */
    public ResponseData queryColthesDeviceByNum(String ordernum){
        List<LaundryClothes> laundryClothes = clothesMapper.queryColthesDeviceByNum(ordernum);
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(laundryClothes);
    }

    /**
     * 商家分配洗衣工序和流程
     * @param laundryOrder
     * @return
     */
    public ResponseData updateOrderLiuG(LaundryOrder laundryOrder){
        laundryOrder.setReservationstatus("2");
        return orderMapper.updateByPrimaryKeySelective(laundryOrder) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("分配洗衣任务失败");
    }

    /**
     * 上门预约时间确认
     * @param laundryOrder
     * @return
     */
    public ResponseData updateUpdoorStatus(LaundryOrder laundryOrder){
        return orderMapper.updateByPrimaryKeySelective(laundryOrder) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("分配洗衣任务失败");
    }

    /**
     * 查询全部工序
     * @return
     */
    public ResponseData queryWorkingprocedureList(){
        List<Workingprocedure> workingprocedures = workingprocedureMapper.selectAll();
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(workingprocedures);
    }

    /**
     * 查询全部流程
     * @return
     */
    public ResponseData queryTechnologicalprocessList(){
        List<Technologicalprocess> technologicalprocesses = technologicalprocessMapper.selectAll();
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(technologicalprocesses);
    }
}
