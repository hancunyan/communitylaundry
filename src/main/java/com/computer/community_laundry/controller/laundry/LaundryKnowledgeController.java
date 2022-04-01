package com.computer.community_laundry.controller.laundry;

import com.computer.community_laundry.service.laundry.LaundryKnowledgeService;
import com.computer.community_laundry.support.entity.laundry.LaundryKnowledgeWithBLOBs;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 洗衣常识 促销活动业务
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("knowledge")
public class LaundryKnowledgeController {

    final LaundryKnowledgeService laundryKnowledgeService;

    /**
     * 洗衣常识分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "queryKnowledge",method = RequestMethod.GET)
    public LayUIPage queryKnowledge(int page,int limit){
        return laundryKnowledgeService.queryKnowledge(page, limit);
    }

    /**
     * 促销活动分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "queryPromotion",method = RequestMethod.GET)
    public LayUIPage queryPromotion(int page, int limit){
        return laundryKnowledgeService.queryPromotion(page, limit);
    }

    /**
     * 添加常识 促销
     * @param laundryKnowledgeWithBLOBs
     * @return
     */
    @RequestMapping(value = "insertKnowledge",method = RequestMethod.POST)
    public ResponseData insertKnowledge(@RequestBody LaundryKnowledgeWithBLOBs laundryKnowledgeWithBLOBs){
        return laundryKnowledgeService.insertKnowledge(laundryKnowledgeWithBLOBs);
    }

    /**
     * 修改常识 促销
     * @param laundryKnowledgeWithBLOBs
     * @return
     */
    @RequestMapping(value = "updateKnowledge",method = RequestMethod.PUT)
    public ResponseData updateKnowledge(@RequestBody LaundryKnowledgeWithBLOBs laundryKnowledgeWithBLOBs){
        return laundryKnowledgeService.updateKnowledge(laundryKnowledgeWithBLOBs);
    }
    /**
     * 删除常识 促销
     * @param id
     * @return
     */
    @RequestMapping(value = "delKnowledge",method = RequestMethod.DELETE)
    public ResponseData delKnowledge(@RequestParam("id") Integer id){
        return laundryKnowledgeService.delKnowledge(id);
    }
}
