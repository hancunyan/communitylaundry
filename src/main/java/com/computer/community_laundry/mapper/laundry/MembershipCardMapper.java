package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.MembershipCard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MembershipCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MembershipCard record);

    int insertSelective(MembershipCard record);

    MembershipCard selectByPrimaryKey(Integer id);
    // 会员信息条件查询
    List<MembershipCard> queryMembershipCardPaging(String telephone);

    int updateByPrimaryKeySelective(MembershipCard record);

    int updateByPrimaryKey(MembershipCard record);
}