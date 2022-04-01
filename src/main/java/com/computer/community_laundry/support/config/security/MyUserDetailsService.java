package com.computer.community_laundry.support.config.security;

import com.computer.community_laundry.service.system.RoleResourceService;
import com.computer.community_laundry.service.system.SysUserService;
import com.computer.community_laundry.support.entity.system.SysResource;
import com.computer.community_laundry.support.entity.system.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 自定义权限资源分配
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    final SysUserService userService;

    final RoleResourceService roleResourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> auths = new ArrayList<>();
        SysUser sysUser = userService.queryUserByName(username);
        if (sysUser == null) {
            throw new AuthenticationServiceException("用户名不存在");
        }
        List<SysResource> sysResources = roleResourceService.queryPermissionByUserId(sysUser.getUserid());
        for (SysResource sysResource : sysResources) {
            auths.add(new SimpleGrantedAuthority(sysResource.getResourcename().toUpperCase()));
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), true, true, true, true, auths);
    }
}
