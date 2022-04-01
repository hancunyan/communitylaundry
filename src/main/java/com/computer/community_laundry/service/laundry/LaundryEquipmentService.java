package com.computer.community_laundry.service.laundry;

import com.computer.community_laundry.mapper.laundry.LaundryEquipmentMapper;
import com.computer.community_laundry.mapper.laundry.PriceListMapper;
import com.computer.community_laundry.support.entity.laundry.LaundryEquipment;
import com.computer.community_laundry.support.entity.laundry.PriceList;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 设备管理业务实现
 */
@RequiredArgsConstructor
@Service
public class LaundryEquipmentService {
    final static Logger logger = LoggerFactory.getLogger(LaundryEquipmentService.class);

    final LaundryEquipmentMapper equipmentMapper;

    final PriceListMapper priceListMapper;

    /**
     * 分页条件查询设备信息
     * @param page  当前页
     * @param limit  每页条数
     * @param ename  设备名称
     * @param estate  设备状态
     * @return
     */
    public LayUIPage queryEquipmentPaging(int page,int limit,String ename,String estate){
        PageHelper.startPage(page,limit);
        List<LaundryEquipment> laundryEquipments = equipmentMapper.queryEquipmentPaging(ename, estate);
        PageInfo pageInfo = new PageInfo(laundryEquipments);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 新增设备信息
     * @param equipment
     * @return
     */
    public ResponseData insertEquipments(LaundryEquipment equipment){
        return equipmentMapper.insertSelective(equipment) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("洗衣设备新增失败");
    }

    /**
     * 编辑设备信息
     * @param equipment
     * @return
     */
    public ResponseData updateEquipments(LaundryEquipment equipment){
        return equipmentMapper.updateByPrimaryKeySelective(equipment) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("洗衣设备修改失败");
    }

    /**
     * 删除设备信息
     * @param equipmentid
     * @return
     */
    public ResponseData delEquipments(Integer equipmentid){
        return equipmentMapper.deleteByPrimaryKey(equipmentid) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("洗衣设备删除失败");
    }

    /**
     * 洗涤对象查询
     * @return
     */
    public ResponseData queryWashingobjec(){
        List<PriceList> priceLists = priceListMapper.queryWashingobjec();
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(priceLists);
    }
}
