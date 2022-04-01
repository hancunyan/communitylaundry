package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.RoleResource;
import com.computer.community_laundry.support.entity.system.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer id);
    // 根据角色id删除该角色绑定的权限信息
    int deleteByRoleId(Integer roleid);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer id);
    //根据角色id查询该角色下的权限信息
    List<RoleResource> selectRoleResourceByRoleId(Integer roleid);
    //根据用户id 查询用户所拥有的权限页面
    List<SysResource> queryPermissionByUserId(Integer userid);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}