package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.PriceList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PriceListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PriceList record);

    int insertSelective(PriceList record);

    PriceList selectByPrimaryKey(Integer id);

    List<PriceList> queryWashingobjec();

    int updateByPrimaryKeySelective(PriceList record);

    int updateByPrimaryKey(PriceList record);
}