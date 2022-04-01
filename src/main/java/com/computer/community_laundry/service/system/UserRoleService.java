package com.computer.community_laundry.service.system;

import com.computer.community_laundry.mapper.system.UserRoleMapper;
import com.computer.community_laundry.support.entity.system.UserRole;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户角色关联业务实现
 */
@Service
@RequiredArgsConstructor
public class UserRoleService {
    final static Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    final UserRoleMapper userRoleMapper;

    /**
     * 通过用户id查询该用户绑定的角色信息
     * @param userId
     * @return
     */
    public ResponseData queryRoleByUserid(Integer userId){
        List<UserRole> userRoles = userRoleMapper.selectUserRoleByUserId(userId);
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(userRoles);
    }
}
