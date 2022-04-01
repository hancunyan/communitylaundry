package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.MembershipLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MembershipLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MembershipLevel record);

    int insertSelective(MembershipLevel record);

    MembershipLevel selectByPrimaryKey(Integer id);

    List<MembershipLevel> queryMembershipLevelPaging(String gradename);

    int updateByPrimaryKeySelective(MembershipLevel record);

    int updateByPrimaryKey(MembershipLevel record);
}