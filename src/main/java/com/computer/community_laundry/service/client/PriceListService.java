package com.computer.community_laundry.service.client;

import com.computer.community_laundry.mapper.laundry.PriceListMapper;
import com.computer.community_laundry.support.entity.laundry.PriceList;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 服务品种业务实现
 */
@Service
@RequiredArgsConstructor
public class PriceListService {

    final PriceListMapper priceListMapper;

    /**
     * 分页条件查询服务品种
     * @param page
     * @param limit
     * @return
     */
    public LayUIPage queryPriceList(int page,int limit){
        PageHelper.startPage(page, limit);
        List<PriceList> priceLists = priceListMapper.queryWashingobjec();
        PageInfo pageInfo = new PageInfo(priceLists);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 添加服务品种
     * @param priceList
     * @return
     */
    public ResponseData insetPrice(PriceList priceList){
        return priceListMapper.insertSelective(priceList) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 修改服务品种
     * @param priceList
     * @return
     */
    public ResponseData upPrice(PriceList priceList){
        return priceListMapper.updateByPrimaryKeySelective(priceList) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }

    /**
     * 删除id
     * @param id
     * @return
     */
    public ResponseData delPrice(Integer id){
        return priceListMapper.deleteByPrimaryKey(id) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse();
    }
}
