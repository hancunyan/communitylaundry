package com.computer.community_laundry.controller.laundry;

import com.computer.community_laundry.service.laundry.OrderService;
import com.computer.community_laundry.support.entity.laundry.LaundryOrder;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 商家端订单业务实现
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("laundryorder")
public class OrderController {
    final OrderService orderService;

    /**
     * 分页条件查询订单信息（商家端）
     * @param page
     * @param limit
     * @param mobiles
     * @return
     */
    @RequestMapping(value = "queryOrderPaging",method = RequestMethod.GET)
    public LayUIPage queryOrderPaging(@RequestParam("page") int page,
                                      @RequestParam("limit") int limit,
                                      @RequestParam(value = "state",required = false) String state,
                                      @RequestParam(value = "mobiles",required = false) String mobiles){
        return orderService.queryOrderPaging(page, limit, state, mobiles);
    }

    /**
     * 根据订单编号查询当前订单下衣服信息
     * @param ordernum
     * @return
     */
    @RequestMapping(value = "queryColthesByNum",method = RequestMethod.GET)
    public ResponseData queryColthesByNum(@RequestParam(value = "ordernum") String ordernum){
        return orderService.queryColthesByNum(ordernum);
    }

    /**
     * 根据订单编号查询当前订单下衣服信息及设备信息
     * @param ordernum
     * @return
     */
    @RequestMapping(value = "queryColthesDeviceByNum",method = RequestMethod.GET)
    public ResponseData queryColthesDeviceByNum(@RequestParam(value = "ordernum") String ordernum){
        return orderService.queryColthesDeviceByNum(ordernum);
    }

    /**
     * 商家分配洗衣工序和流程
     * @param laundryOrder
     * @return
     */
    @RequestMapping(value = "updateOrderLiuG",method = RequestMethod.POST)
    public ResponseData updateOrderLiuG(@RequestBody LaundryOrder laundryOrder){
        return orderService.updateOrderLiuG(laundryOrder);
    }

    /**
     * 上门预约时间确认
     * @param laundryOrder
     * @return
     */
    @RequestMapping(value = "updateUpdoorStatus",method = RequestMethod.POST)
    public ResponseData updateUpdoorStatus(@RequestBody LaundryOrder laundryOrder){
        return orderService.updateUpdoorStatus(laundryOrder);
    }

    /**
     * 查询全部工序
     * @return
     */
    @RequestMapping(value = "queryWorkingprocedureList",method = RequestMethod.GET)
    public ResponseData queryWorkingprocedureList(){
        return orderService.queryWorkingprocedureList();
    }

    /**
     * 查询全部流程
     * @return
     */
    @RequestMapping(value = "queryTechnologicalprocessList",method = RequestMethod.GET)
    public ResponseData queryTechnologicalprocessList(){
        return orderService.queryTechnologicalprocessList();
    }

    /**
     * 分配设备
     * @param laundryOrder
     * @return
     */
    @RequestMapping(value = "upLaundryService",method = RequestMethod.POST)
    public ResponseData upLaundryService(@RequestBody LaundryOrder laundryOrder){
        return orderService.upLaundryService(laundryOrder);
    }
}
