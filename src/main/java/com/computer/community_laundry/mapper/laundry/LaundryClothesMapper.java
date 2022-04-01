package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.LaundryClothes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LaundryClothesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LaundryClothes record);

    int insertSelective(LaundryClothes record);

    LaundryClothes selectByPrimaryKey(Integer id);

    List<LaundryClothes> queryColthesList();
    //根据订单编号查询衣服信息
    List<LaundryClothes> queryColthesByNum(String ordernum);
    //根据订单编号查询衣服信息及设备
    List<LaundryClothes> queryColthesDeviceByNum(String ordernum);
    // 根据工序id查询衣服信息
    List<LaundryClothes> queryColthesByWorking(String working,String ordernum);
    // 根据流程id查询衣服信息
    List<LaundryClothes> queryColthesByTechnological(String technological,String ordernum);
    // 查询设备容量
    Map<String,Integer> queryCapacity(Integer eid);

    int updateByPrimaryKeySelective(LaundryClothes record);

    int updateByPrimaryKey(LaundryClothes record);
}