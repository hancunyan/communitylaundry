package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    // 删除角色信息
    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);
    // 添加角色信息
    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleid);
    // 条件查询角色信息
    List<SysRole> selectRoleList(String rolename);
    // 查询全部角色信息
    List<SysRole> selectAllRoles();
    // 修改角色信息
    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}