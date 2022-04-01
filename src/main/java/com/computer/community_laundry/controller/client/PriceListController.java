package com.computer.community_laundry.controller.client;

import com.computer.community_laundry.service.client.PriceListService;
import com.computer.community_laundry.support.entity.laundry.PriceList;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 服务品种查询
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("price")
public class PriceListController {

    final PriceListService priceListService;

    /**
     * 分页条件查询服务品种
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "queryPriceList",method = RequestMethod.GET)
    public LayUIPage queryPriceList(int page, int limit){
        return priceListService.queryPriceList(page, limit);
    }

    /**
     * 添加服务品种
     * @param priceList
     * @return
     */
    @RequestMapping(value = "insetPrice",method = RequestMethod.POST)
    public ResponseData insetPrice(@RequestBody PriceList priceList){
        return priceListService.insetPrice(priceList);
    }

    /**
     * 修改服务品种
     * @param priceList
     * @return
     */
    @RequestMapping(value = "upPrice",method = RequestMethod.PUT)
    public ResponseData upPrice(@RequestBody PriceList priceList){
        return priceListService.upPrice(priceList);
    }

    /**
     * 删除id
     * @param id
     * @return
     */
    @RequestMapping(value = "delPrice",method = RequestMethod.DELETE)
    public ResponseData delPrice(Integer id){
        return priceListService.delPrice(id);
    }
}
