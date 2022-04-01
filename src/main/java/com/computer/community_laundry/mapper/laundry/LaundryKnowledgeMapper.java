package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.LaundryKnowledge;
import com.computer.community_laundry.support.entity.laundry.LaundryKnowledgeWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LaundryKnowledgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LaundryKnowledgeWithBLOBs record);

    int insertSelective(LaundryKnowledgeWithBLOBs record);

    LaundryKnowledgeWithBLOBs selectByPrimaryKey(Integer id);

    List<LaundryKnowledgeWithBLOBs> queryKnowledge();

    List<LaundryKnowledgeWithBLOBs> queryPromotion();

    int updateByPrimaryKeySelective(LaundryKnowledgeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LaundryKnowledgeWithBLOBs record);

    int updateByPrimaryKey(LaundryKnowledge record);
}