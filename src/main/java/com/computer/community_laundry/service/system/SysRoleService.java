package com.computer.community_laundry.service.system;

import com.computer.community_laundry.mapper.system.RoleResourceMapper;
import com.computer.community_laundry.mapper.system.SysRoleMapper;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.entity.system.RoleResource;
import com.computer.community_laundry.support.entity.system.SysRole;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 角色信息业务实现
 */
@Service
@RequiredArgsConstructor
public class SysRoleService {
    final static Logger logger = LoggerFactory.getLogger(SysRoleService.class);

    final SysRoleMapper roleMapper;

    final RoleResourceMapper roleResourceMapper;

    /**
     *  分页条件查询角色信息
     * @param page 当前页
     * @param limit 每页条数
     * @param rolename 角色名称
     * @return
     */
    public LayUIPage selectRoleList(int page, int limit, String rolename){
        PageHelper.startPage(page, limit);
        List<SysRole> sysRoles = roleMapper.selectRoleList(rolename);
        PageInfo pageInfo = new PageInfo(sysRoles);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        return layUIPage;
    }

    /**
     * 查询全部角色信息
     * @return
     */
    public ResponseData selectAllRoles(){
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(roleMapper.selectAllRoles());
    }

    /**
     * 添加角色信息
     * @param role 角色信息实体
     * @return
     */
    @Transactional
    public ResponseData insertRole(SysRole role){
        int addResult = roleMapper.insertSelective(role);
        if(addResult > 0){
            String[] resourceIdArr = role.getResourceid().split(",");
            for (int i = 0; i < resourceIdArr.length; i++) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleid(role.getRoleid());
                roleResource.setResourceid(Integer.valueOf(resourceIdArr[i]));
                addResult = roleResourceMapper.insertSelective(roleResource);
            }
        }
        return addResult > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("角色信息添加失败！");
    }

    /**
     * 修改角色信息
     * @param role 角色信息实体
     * @return
     */
    public ResponseData updateRole(SysRole role){
        return roleMapper.updateByPrimaryKeySelective(role) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("角色信息修改失败！");
    }

    /**
     * 删除角色信息
     * @param roleId 角色id
     * @return
     */
    public ResponseData delRole(Integer roleId){
        return roleMapper.deleteByPrimaryKey(roleId) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("角色信息删除失败！");
    }
}
