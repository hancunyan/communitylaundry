package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.LaundryOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LaundryOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LaundryOrder record);

    int insertSelective(LaundryOrder record);

    LaundryOrder selectByPrimaryKey(Integer id);
    //商家端订单列表分页查询
    List<LaundryOrder> queryOrderPaging(String phone,String status);
    //客户端待支付列表查询
    List<LaundryOrder> queryOrderPagingPayment();
    //品种价格列表查询
    List<PriceListMapper> queryPriceList();

    int updateByPrimaryKeySelective(LaundryOrder record);

    int updateByPrimaryKey(LaundryOrder record);
}