package com.computer.community_laundry.controller.laundry;

import com.computer.community_laundry.service.laundry.MembershipService;
import com.computer.community_laundry.support.entity.laundry.MembershipCard;
import com.computer.community_laundry.support.entity.laundry.MembershipLevel;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 商家端会员业务接口
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MembershipController {

    final MembershipService membershipService;


    /**
     * 会员信息分页条件查询
     * @param page
     * @param limit
     * @param telephone
     * @return
     */
    @RequestMapping(value = "queryMembershipCardPaging",method = RequestMethod.GET)
    public LayUIPage queryMembershipCardPaging(int page, int limit,@RequestParam(value = "telephone",required = false) String telephone){
        return membershipService.queryMembershipCardPaging(page, limit, telephone);
    }

    /**
     * 会员等级信息分页条件查询
     * @param page
     * @param limit
     * @param gradename
     * @return
     */
    @RequestMapping(value = "queryMembershipLevelPaging",method = RequestMethod.GET)
    public LayUIPage queryMembershipLevelPaging(int page,int limit,@RequestParam(value = "gradename",required = false) String gradename){
        return membershipService.queryMembershipLevelPaging(page, limit, gradename);
    }

    /**
     * 添加会员信息
     * @param membershipCard
     * @return
     */
    @RequestMapping(value = "insertCard",method = RequestMethod.POST)
    public ResponseData insertCard(@RequestBody MembershipCard membershipCard){
        return membershipService.insertCard(membershipCard);
    }

    /**
     * 修改会员信息
     * @param membershipCard
     * @return
     */
    @RequestMapping(value = "upCard",method = RequestMethod.PUT)
    public ResponseData upCard(@RequestBody MembershipCard membershipCard){
        return membershipService.upCard(membershipCard);
    }

    /**
     * 删除会员信息
     * @param id
     * @return
     */
    @RequestMapping(value = "delCard",method = RequestMethod.DELETE)
    public ResponseData delCard(Integer id){
        return membershipService.delCard(id);
    }

    /**
     * 添加会员等级信息
     * @param membershipLevel
     * @return
     */
    @RequestMapping(value = "insertLevel",method = RequestMethod.POST)
    public ResponseData insertLevel(@RequestBody MembershipLevel membershipLevel){
        return membershipService.insertLevel(membershipLevel);
    }

    /**
     * 修改会员等级信息
     * @param membershipLevel
     * @return
     */
    @RequestMapping(value = "upLevel",method = RequestMethod.PUT)
    public ResponseData upLevel(@RequestBody MembershipLevel membershipLevel){
        return membershipService.upLevel(membershipLevel);
    }

    /**
     * 删除会员等级信息
     * @param id
     * @return
     */
    @RequestMapping(value = "delLevel",method = RequestMethod.DELETE)
    public ResponseData delLevel(Integer id){
        return membershipService.delLevel(id);
    }
}
