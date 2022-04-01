package com.computer.community_laundry.controller.system;

import com.computer.community_laundry.service.system.SysRoleService;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.entity.system.SysRole;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 角色信息接口实现
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("sysrole")
public class SysRoleController {
    final static Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    final SysRoleService roleService;

    /**
     *  分页条件查询角色信息
     * @param page 当前页
     * @param limit 每页条数
     * @param rolename 角色名称
     * @return
     */
    @RequestMapping(value = "selectRoleList",method = RequestMethod.GET)
    public LayUIPage selectRoleList(int page, int limit, @RequestParam(value = "rolename",required = false) String rolename){
        return roleService.selectRoleList(page, limit, rolename);
    }

    @RequestMapping(value = "selectAllRoles",method = RequestMethod.GET)
    public ResponseData selectAllRoles(){
        return roleService.selectAllRoles();
    }

    /**
     * 添加角色信息
     * @param role 角色信息实体
     * @return
     */
    @RequestMapping(value = "insertRole",method = RequestMethod.POST)
    public ResponseData insertRole(@RequestBody SysRole role){
        return roleService.insertRole(role);
    }

    /**
     * 修改角色信息
     * @param role 角色信息实体
     * @return
     */
    @RequestMapping(value = "updateRole",method = RequestMethod.PUT)
    public ResponseData updateRole(@RequestBody SysRole role){
        return roleService.updateRole(role);
    }

    /**
     * 删除角色信息
     * @param roleId 角色id
     * @return
     */
    @RequestMapping(value = "delRole",method = RequestMethod.DELETE)
    public ResponseData delRole(@RequestParam("roleId") int roleId){
        return roleService.delRole(roleId);
    }
}
