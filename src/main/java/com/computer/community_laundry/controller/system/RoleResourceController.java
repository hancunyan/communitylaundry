package com.computer.community_laundry.controller.system;

import com.computer.community_laundry.service.system.RoleResourceService;
import com.computer.community_laundry.support.entity.system.SysResource;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 角色权限设置接口实现
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("roleresource")
public class RoleResourceController {
    final static Logger logger = LoggerFactory.getLogger(RoleResourceController.class);

    final RoleResourceService roleResourceService;


    /**
     * 通过角色id查询该角色下绑定的权限信息
     * @param roleId
     * @return
     */
    @RequestMapping(value = "queryResourceByRoleId",method = RequestMethod.GET)
    public ResponseData queryResourceByRoleId(@RequestParam("roleId") int roleId){
        return roleResourceService.queryResourceByRoleId(roleId);
    }

    /**
     * 菜单树查询
     * @return
     */
    @RequestMapping(value = "queryResourceTree",method = RequestMethod.GET)
    public ResponseData queryResourceTree(){
        return roleResourceService.queryResourceTree();
    }

    /**
     * 查询权限页面
     * @return
     */
    @RequestMapping(value = "queryPermissionByUserId",method = RequestMethod.GET)
    public List<SysResource> queryPermissionByUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        int userid = (int) session.getAttribute("userid");
        return roleResourceService.queryPermissionTreeByUserId(userid);
    }
}
