package com.computer.community_laundry.service.system;

import com.computer.community_laundry.mapper.system.RoleResourceMapper;
import com.computer.community_laundry.mapper.system.SysResourceMapper;
import com.computer.community_laundry.support.entity.system.RoleResource;
import com.computer.community_laundry.support.entity.system.SysResource;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.computer.community_laundry.support.utils.ResourceTree;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色权限业务实现
 */
@RequiredArgsConstructor
@Service
public class RoleResourceService {
    final static Logger logger = LoggerFactory.getLogger(RoleResourceService.class);

    final RoleResourceMapper roleResourceMapper;

    final SysResourceMapper resourceMapper;

    /**
     * 通过角色id查询该角色下绑定的权限信息
     * @param roleId
     * @return
     */
    public ResponseData queryResourceByRoleId(Integer roleId){
        List<RoleResource> roleResources = roleResourceMapper.selectRoleResourceByRoleId(roleId);
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(roleResources);
    }

    /**
     * 查询用户所拥有的权限页面
     * @param userid
     * @return
     */
    public List<SysResource> queryPermissionByUserId(Integer userid){
        return roleResourceMapper.queryPermissionByUserId(userid);
    }

    /**
     * 查询用户所拥有的权限页面tree结构
     * @param userid
     * @return
     */
    public List<SysResource> queryPermissionTreeByUserId(Integer userid){
        List<SysResource> sysResources = roleResourceMapper.queryPermissionByUserId(userid);
        ResourceTree menuTree =new ResourceTree(sysResources);
        return menuTree.builTree();
    }

    /**
     * 查询全部页面
     * @return
     */
    public List<SysResource> queryAllPage(){
        return resourceMapper.queryAllPage();
    }

    /**
     * 菜单树查询
     * @return
     */
    public ResponseData queryResourceTree(){
        List<SysResource> sysResources = resourceMapper.queryAllResource();
        ResourceTree menuTree =new ResourceTree(sysResources);
        List<SysResource> sysResourcesTree = menuTree.builTree();
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(sysResourcesTree);
    }
}
