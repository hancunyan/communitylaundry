package com.computer.community_laundry.mapper.laundry;

import com.computer.community_laundry.support.entity.laundry.Workingprocedure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkingprocedureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workingprocedure record);

    int insertSelective(Workingprocedure record);

    Workingprocedure selectByPrimaryKey(Integer id);
    // 查询全部工序
    List<Workingprocedure> selectAll();

    int updateByPrimaryKeySelective(Workingprocedure record);

    int updateByPrimaryKey(Workingprocedure record);
}