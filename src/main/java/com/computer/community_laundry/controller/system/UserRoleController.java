package com.computer.community_laundry.controller.system;

import com.computer.community_laundry.service.system.UserRoleService;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户角色关联接口实现
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("userrole")
public class UserRoleController {
    final static Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    final UserRoleService userRoleService;

    /**
     * 通过用户id查询该用户绑定的角色信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "queryRoleByUserid",method = RequestMethod.GET)
    public ResponseData queryRoleByUserid(@RequestParam("userId") Integer userId){
        return userRoleService.queryRoleByUserid(userId);
    }
}
