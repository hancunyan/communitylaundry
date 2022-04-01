package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.OperationLog;
import com.computer.community_laundry.support.entity.system.OperationLogWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogMapper {

    OperationLogWithBLOBs selectByPrimaryKey(Integer id);
    // 条件查询操作日志信息
    List<OperationLogWithBLOBs> queryOperLogPaging(@Param("username") String username, @Param("opermodel") String opermodel, @Param("opertype") String opertype, @Param("startTime") String startTime, @Param("endTime") String endTime);

    int deleteByPrimaryKey(@Param("id") Integer id);
    //操作日志添加
    int insert(OperationLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OperationLogWithBLOBs record);

    int updateByPrimaryKey(OperationLog record);
}