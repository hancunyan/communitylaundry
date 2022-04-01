package com.computer.community_laundry.controller.system;

import com.computer.community_laundry.service.system.SysUserService;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.entity.system.SysUser;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 账号信息接口实现
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("sysuser")
public class SysUserController {

    final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    final SysUserService userService;

    /**
     * 分页条件查询账号信息
     * @param page  当前页
     * @param limit  每页条数
     * @param username 用户名
     * @param mobiles  联系电话
     * @param usertype  人员类别
     * @return
     */
    @RequestMapping(path = "selectUserPageInfo",method = RequestMethod.GET)
    public LayUIPage selectUserPageInfo(int page, int limit,
                                        @RequestParam(value = "username",required = false) String username,
                                        @RequestParam(value = "mobiles",required = false) String mobiles,
                                        @RequestParam(value = "usertype",required = false) String usertype){
        return userService.selectUserPageInfo(page, limit, username, mobiles, usertype);
    }

    /**
     * 账号信息添加
     * @param user 账号信息实体
     * @return
     */
    @RequestMapping(path = "insertSysUser",method = RequestMethod.POST)
    public ResponseData insertSysUser(@RequestBody SysUser user){
        return userService.insertSysUser(user);
    }

    /**
     * 账号信息修改
     * @param user 账号信息实体
     * @return
     */
    @RequestMapping(path = "updateSysUser",method = RequestMethod.PUT)
    public ResponseData updateSysUser(@RequestBody SysUser user){
        return userService.updateSysUser(user);
    }

    /**
     * 账号信息删除
     * @param userId 账号id
     * @return
     */
    @RequestMapping(path = "delSysUser",method = RequestMethod.DELETE)
    public ResponseData delSysUser(@RequestParam(value = "userId") int userId){
        return userService.delSysUser(userId);
    }

    /**
     * 账号信息状态修改（1 正常  2 注销）
     * @param user 账号信息实体
     * @return
     */
    @RequestMapping(path = "updateStateById",method = RequestMethod.PUT)
    public ResponseData updateStateById(@RequestBody SysUser user){
        return userService.updateState(user);
    }

    /**
     * 密码修改
     * @param user
     * @return
     */
    @RequestMapping(path = "updatePass",method = RequestMethod.PUT)
    public ResponseData updatePass(@RequestBody SysUser user){
        return userService.updatePass(user);
    }
}
