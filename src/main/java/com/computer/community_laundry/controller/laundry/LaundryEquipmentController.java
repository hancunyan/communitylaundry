package com.computer.community_laundry.controller.laundry;

import com.computer.community_laundry.service.laundry.LaundryEquipmentService;
import com.computer.community_laundry.support.entity.laundry.LaundryEquipment;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 设备管理接口实现
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("equipment")
public class LaundryEquipmentController {
    final static Logger logger = LoggerFactory.getLogger(LaundryEquipmentController.class);

    final LaundryEquipmentService laundryEquipmentService;

    /**
     * 分页条件查询设备信息
     * @param page  当前页
     * @param limit  每页条数
     * @param equipmentname  设备名称
     * @param state  设备状态
     * @return
     */
    @RequestMapping(value = "queryEquipmentPaging",method = RequestMethod.GET)
    public LayUIPage queryEquipmentPaging(@RequestParam("page") int page,@RequestParam("limit") int limit,
                                          @RequestParam(value = "equipmentname",required = false) String equipmentname,
                                          @RequestParam(value = "state",required = false) String state){
        return laundryEquipmentService.queryEquipmentPaging(page, limit, equipmentname, state);
    }

    /**
     * 新增设备信息
     * @param equipment
     * @return
     */
    @RequestMapping(value = "insertEquipments",method = RequestMethod.POST)
    public ResponseData insertEquipments(@RequestBody LaundryEquipment equipment){
        return laundryEquipmentService.insertEquipments(equipment);
    }

    /**
     * 编辑设备信息
     * @param equipment
     * @return
     */
    @RequestMapping(value = "updateEquipments",method = RequestMethod.PUT)
    public ResponseData updateEquipments(@RequestBody LaundryEquipment equipment){
        return laundryEquipmentService.updateEquipments(equipment);
    }

    /**
     * 删除设备信息
     * @param equipmentid
     * @return
     */
    @RequestMapping(value = "delEquipments",method = RequestMethod.DELETE)
    public ResponseData delEquipments(@RequestParam("equipmentid") Integer equipmentid){
        return laundryEquipmentService.delEquipments(equipmentid);
    }

    @RequestMapping(value = "queryWashingobjec",method = RequestMethod.GET)
    public ResponseData queryWashingobjec(){
        return laundryEquipmentService.queryWashingobjec();
    }
}
