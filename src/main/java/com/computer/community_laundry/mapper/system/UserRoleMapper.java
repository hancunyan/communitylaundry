package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);
    // 删除该用户下绑定角色信息
    int deleteByUserId(Integer userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);
    // 根据用户id查询该用户下角色信息
    List<UserRole> selectUserRoleByUserId(Integer userid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}