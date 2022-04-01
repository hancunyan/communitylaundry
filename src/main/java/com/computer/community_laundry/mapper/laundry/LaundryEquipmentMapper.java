package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.LaundryEquipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LaundryEquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LaundryEquipment record);

    int insertSelective(LaundryEquipment record);

    LaundryEquipment selectByPrimaryKey(Integer id);
    // 查询洗衣设备是空闲状态的设备信息
    List<LaundryEquipment> queryEquipmentByStatus(String status);

    List<LaundryEquipment> queryEquipmentCapacity();

    List<LaundryEquipment> queryEquipmentPaging(String ename,String estate);

    int updateByPrimaryKeySelective(LaundryEquipment record);

    int updateByPrimaryKey(LaundryEquipment record);
}