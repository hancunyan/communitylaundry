package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer resourceid);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer resourceid);

    //查询全部页面
    List<SysResource> queryAllPage();
    //查询全部资源信息
    List<SysResource> queryAllResource();

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}