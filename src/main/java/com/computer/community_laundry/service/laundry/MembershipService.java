package com.computer.community_laundry.service.laundry;

import com.computer.community_laundry.mapper.laundry.MembershipCardMapper;
import com.computer.community_laundry.mapper.laundry.MembershipLevelMapper;
import com.computer.community_laundry.support.entity.laundry.MembershipCard;
import com.computer.community_laundry.support.entity.laundry.MembershipLevel;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 商家端会员业务实现
 */
@Service
@RequiredArgsConstructor
public class MembershipService {

    final MembershipCardMapper membershipCardMapper;

    final MembershipLevelMapper membershipLevelMapper;

    /**
     * 会员信息分页条件查询
     * @param page
     * @param limit
     * @param telephone
     * @return
     */
    public LayUIPage queryMembershipCardPaging(int page,int limit,String telephone){
        PageHelper.startPage(page, limit);
        List<MembershipCard> membershipCards = membershipCardMapper.queryMembershipCardPaging(telephone);
        PageInfo pageInfo = new PageInfo(membershipCards);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 会员等级信息分页条件查询
     * @param page
     * @param limit
     * @param gradename
     * @return
     */
    public LayUIPage queryMembershipLevelPaging(int page,int limit,String gradename){
        PageHelper.startPage(page, limit);
        List<MembershipLevel> membershipLevels = membershipLevelMapper.queryMembershipLevelPaging(gradename);
        PageInfo pageInfo = new PageInfo(membershipLevels);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 添加会员信息
     * @param membershipCard
     * @return
     */
    public ResponseData insertCard(MembershipCard membershipCard){
        return membershipCardMapper.insertSelective(membershipCard) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 修改会员信息
     * @param membershipCard
     * @return
     */
    public ResponseData upCard(MembershipCard membershipCard){
        return membershipCardMapper.updateByPrimaryKeySelective(membershipCard) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 删除会员信息
     * @param id
     * @return
     */
    public ResponseData delCard(Integer id){
        return membershipCardMapper.deleteByPrimaryKey(id) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 添加会员等级信息
     * @param membershipLevel
     * @return
     */
    public ResponseData insertLevel(MembershipLevel membershipLevel){
        return membershipLevelMapper.insertSelective(membershipLevel) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 修改会员等级信息
     * @param membershipLevel
     * @return
     */
    public ResponseData upLevel(MembershipLevel membershipLevel){
        return membershipLevelMapper.updateByPrimaryKeySelective(membershipLevel) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 删除会员等级信息
     * @param id
     * @return
     */
    public ResponseData delLevel(Integer id){
        return membershipLevelMapper.deleteByPrimaryKey(id) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }
}
