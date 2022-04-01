package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.ExceptionLog;
import com.computer.community_laundry.support.entity.system.ExceptionLogWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExceptionLogMapper {

    ExceptionLogWithBLOBs selectByPrimaryKey(Integer id);
    /*条件查询异常日志信息*/
    List<ExceptionLogWithBLOBs> queryExceLogPaging(@Param("username") String username, @Param("startTime") String startTime, @Param("endTime") String endTime);

    int deleteByPrimaryKey(Integer id);
    // 异常日志添加
    int insert(ExceptionLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExceptionLogWithBLOBs record);

    int updateByPrimaryKey(ExceptionLog record);
}