package com.computer.community_laundry.service.system;

import com.computer.community_laundry.mapper.system.SysUserMapper;
import com.computer.community_laundry.mapper.system.UserRoleMapper;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.entity.system.SysUser;
import com.computer.community_laundry.support.entity.system.UserRole;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 账号信息业务实现类
 */
@Service
@RequiredArgsConstructor
public class SysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    final SysUserMapper userMapper;

    final UserRoleMapper userRoleMapper;

    /**
     * 分页条件查询用户信息
     * @param page
     * @param limit
     * @param username
     * @param mobiles
     * @param usertype
     * @return
     */
    public LayUIPage selectUserPageInfo(int page, int limit, String username,String mobiles,String usertype){
        PageHelper.startPage(page,limit);
        List<SysUser> sysUsers = userMapper.selectUserList(username,mobiles,usertype);
        PageInfo pageInfo = new PageInfo(sysUsers);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 账号信息添加
     * @param user 账号信息实体
     * @return
     */
    @Transactional
    public ResponseData insertSysUser(SysUser user){
        SysUser sysuser = userMapper.queryUserByName(user.getUsername());
        if(sysuser == null){
            return ResponseFactory.createFaildResponse("账号已存在！");
        }
        // TODO 密码加密
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(user.getPassword());
        user.setPassword(password);
        int addResult = userMapper.insertSelective(user);
        if(addResult > 0){
            UserRole userRole = new UserRole();
            userRole.setUserid(user.getUserid());
            userRole.setRoleid(user.getRoleid());
            addResult = userRoleMapper.insertSelective(userRole);
        }
        return addResult > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("账号信息添加失败！");
    }

    /**
     * 账号信息修改
     * @param user 账号信息实体
     * @return
     */
    public ResponseData updateSysUser(SysUser user){
        // TODO 密码加密 加密后更新
        if(StringUtils.isNotBlank(user.getPassword())){
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String password = encode.encode(user.getPassword());
            user.setPassword(password);
        }
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("账号信息修改失败！");
    }

    /**
     * 账号信息删除
     * @param userId 账号id
     * @return
     */
    public ResponseData delSysUser(Integer userId){
        return userMapper.deleteByPrimaryKey(userId) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("账号信息删除失败！");
    }

    /**
     * 账号信息状态修改（1 正常  2 注销）
     * @param user 账号信息实体
     * @return
     */
    public ResponseData updateState(SysUser user){
        return userMapper.updateStateById(user) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("账号注销失败！");
    }

    /**
     * 密码修改
     * @param user
     * @return
     */
    public ResponseData updatePass(SysUser user){
        //TODO 密码加密
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String password = encode.encode(user.getPassword());
        user.setPassword(password);
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("密码修改失败！");
    }

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    public SysUser queryUserByName(String username){
        SysUser user = userMapper.queryUserByName(username);
        if (user == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("userid", user.getUserid());
        session.setAttribute("username", user.getUsername());
        return user;
    }
}
