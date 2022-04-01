package com.computer.community_laundry.mapper.system;

import com.computer.community_laundry.support.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    // 账号信息删除
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);
    // 账号信息添加
    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);
    // 根据用户名查询
    SysUser queryUserByName(String username);
    // 条件查询用户信息信息
    List<SysUser> selectUserList(String username, String mobiles,String usertype);
    // 账号信息修改
    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    // 账号信息状态修改
    int updateStateById(SysUser record);
}