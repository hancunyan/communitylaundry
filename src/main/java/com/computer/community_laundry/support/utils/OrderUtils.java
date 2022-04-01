package com.computer.community_laundry.support.utils;

import com.computer.community_laundry.mapper.laundry.LaundryClothesMapper;
import com.computer.community_laundry.mapper.laundry.LaundryEquipmentMapper;
import com.computer.community_laundry.mapper.laundry.LaundryOrderMapper;
import com.computer.community_laundry.support.entity.laundry.LaundryClothes;
import com.computer.community_laundry.support.entity.laundry.LaundryEquipment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description:
 */
@Component("orderUtils")
public class OrderUtils {
    @Autowired
    LaundryOrderMapper orderMapper;
    @Autowired
    LaundryClothesMapper clothesMapper;
    @Autowired
    LaundryEquipmentMapper equipmentMapper;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<LaundryEquipment> getLaundryEquipments(){
        // 查询空闲洗衣设备
        return equipmentMapper.queryEquipmentByStatus("1");
    }

    public List<LaundryEquipment> getEquipmentsCollect(){
        // 查询完全未使用设备
        List<LaundryEquipment> laundryEquipments = equipmentMapper.queryEquipmentByStatus("1");
        List<LaundryClothes> laundryClothesList = clothesMapper.queryColthesList();
        if(laundryClothesList.size() > 0){
            List<LaundryClothes> collect = laundryClothesList.parallelStream().filter(distinctByKey(LaundryClothes::getEquipmentid)).collect(Collectors.toList());
            return laundryEquipments.stream().filter(equipment -> !collect.stream()
                    .map(e -> e.getEquipmentid()).collect(Collectors.toList())
                    .contains(equipment.getId())).collect(Collectors.toList());
        }
        return laundryEquipments;
    }

    public List<LaundryClothes> getWorkClothes(String workingprocedure,String ordernum){
        // 依据工序 当前订单衣服设定工序相同的衣服查询
        return clothesMapper.queryColthesByWorking(workingprocedure,ordernum);
    }

    public List<LaundryClothes> getTechnologicalClothess(String technologicalprocess,String ordernum){
        //依据流程 当前订单衣服设定流程相同的衣服查询
        return clothesMapper.queryColthesByTechnological(technologicalprocess,ordernum);
    }

    /**
     * 判断当前订单衣服设定工序相同的衣服信息和当前订单衣服设定流程相同的衣服信息
     * 如果有数据 拿出来过滤这些衣服所分配的设备是否容量已满 没有满 就将当前设备分配到该设备中 如果已满 寻找其它适合的设备
     * 如果没有数据那就说明当前订单中衣服设定的工序和流程没有相同的衣服 直接将订单中的衣服 进行 品种和颜色分类放到新的未使用的设备中
     */
    public List<LaundryClothes> getLastClothesList(List<LaundryClothes> workClothes,List<LaundryClothes> technologicalClothes,List<LaundryEquipment> laundryEquipments){
        List<LaundryClothes> clothesList = new ArrayList();
        for (LaundryClothes laundryWorkClothes : workClothes) {
            for (LaundryClothes laundryTechnologicalClothes : technologicalClothes) {
                if(laundryWorkClothes.getEquipmentid() != null && laundryTechnologicalClothes.getEquipmentid() != null){
                    if(laundryWorkClothes.getEquipmentid() == laundryTechnologicalClothes.getEquipmentid()){
                        clothesList.add(laundryWorkClothes);
                        clothesList.add(laundryTechnologicalClothes);
                    }
                }
            }
        }
        List<LaundryClothes> lastClothesList = new ArrayList();
        for (LaundryEquipment laundryEquipment : laundryEquipments) {
            for (LaundryClothes laundryClothes : clothesList) {
                if(laundryEquipment.getId() == laundryClothes.getEquipmentid()){
                    lastClothesList.add(laundryClothes);
                }
            }
        }
        return lastClothesList;
    }

    public Map<String, Integer> getCapacity(int equipmentid){
        Map<String, Integer> stringObjectMap = clothesMapper.queryCapacity(equipmentid);
        // 设备容量
        Integer capacity = stringObjectMap.get("capacity");
        // 已占用容量
        Integer clocount = stringObjectMap.get("clocount");
        return stringObjectMap;
    }

    public List<LaundryClothes> lastList(List<LaundryClothes> workClothes,List<LaundryClothes> technologicalClothes){
        return getLastClothesList(workClothes, technologicalClothes, getLaundryEquipments());
    }
}
