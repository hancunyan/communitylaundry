package com.computer.community_laundry.service.laundry;

import com.computer.community_laundry.mapper.laundry.LaundryKnowledgeMapper;
import com.computer.community_laundry.support.entity.laundry.LaundryKnowledgeWithBLOBs;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 洗衣常识 促销活动
 */
@Service
@RequiredArgsConstructor
public class LaundryKnowledgeService {
    final LaundryKnowledgeMapper knowledgeMapper;

    /**
     * 洗衣常识分页查询
     * @param page
     * @param limit
     * @return
     */
    public LayUIPage queryKnowledge(int page,int limit){
        PageHelper.startPage(page, limit);
        List<LaundryKnowledgeWithBLOBs> laundryKnowledgeWithBLOBs = knowledgeMapper.queryKnowledge();
        PageInfo pageInfo = new PageInfo(laundryKnowledgeWithBLOBs);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        return layUIPage;
    }

    /**
     * 促销活动分页查询
     * @param page
     * @param limit
     * @return
     */
    public LayUIPage queryPromotion(int page,int limit){
        PageHelper.startPage(page, limit);
        List<LaundryKnowledgeWithBLOBs> laundryPromotionWithBLOBs = knowledgeMapper.queryPromotion();
        PageInfo pageInfo = new PageInfo(laundryPromotionWithBLOBs);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setData(pageInfo.getList());
        layUIPage.setCount(pageInfo.getTotal());
        return layUIPage;
    }

    /**
     * 添加常识 促销
     * @param laundryKnowledgeWithBLOBs
     * @return
     */
    public ResponseData insertKnowledge(LaundryKnowledgeWithBLOBs laundryKnowledgeWithBLOBs){
        return knowledgeMapper.insertSelective(laundryKnowledgeWithBLOBs) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 修改常识 促销
     * @param laundryKnowledgeWithBLOBs
     * @return
     */
    public ResponseData updateKnowledge(LaundryKnowledgeWithBLOBs laundryKnowledgeWithBLOBs){
        return knowledgeMapper.updateByPrimaryKeySelective(laundryKnowledgeWithBLOBs) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }
    /**
     * 删除常识 促销
     * @param id
     * @return
     */
    public ResponseData delKnowledge(Integer id){
        return knowledgeMapper.deleteByPrimaryKey(id) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }
}
