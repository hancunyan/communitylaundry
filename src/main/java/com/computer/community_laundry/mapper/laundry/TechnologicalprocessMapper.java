package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.Technologicalprocess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologicalprocessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Technologicalprocess record);

    int insertSelective(Technologicalprocess record);

    Technologicalprocess selectByPrimaryKey(Integer id);
    // 查询全部流程信息
    List<Technologicalprocess> selectAll();

    int updateByPrimaryKeySelective(Technologicalprocess record);

    int updateByPrimaryKey(Technologicalprocess record);
}