package com.computer.community_laundry.service.client;

import com.computer.community_laundry.mapper.laundry.LaundryClothesMapper;
import com.computer.community_laundry.mapper.laundry.LaundryOrderMapper;
import com.computer.community_laundry.support.entity.laundry.LaundryClothes;
import com.computer.community_laundry.support.entity.laundry.LaundryOrder;
import com.computer.community_laundry.support.entity.system.LayUIPage;
import com.computer.community_laundry.support.responses.OptStatusCode;
import com.computer.community_laundry.support.responses.ResponseFactory;
import com.computer.community_laundry.support.responses.imlp.ResponseData;
import com.computer.community_laundry.support.utils.RandUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 客户端洗衣业务实现
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    final LaundryClothesMapper clothesMapper;

    final LaundryOrderMapper orderMapper;

    /**
     * 客户端待支付列表查询
     * @param page 当前页
     * @param limit 每页条数
     * @return
     */
    public LayUIPage queryPaymentOrder(int page,int limit){
        PageHelper.startPage(page,limit);
        List<LaundryOrder> laundryOrders = orderMapper.queryOrderPagingPayment();
        for (LaundryOrder orders: laundryOrders) {
            orders.setClothesList(clothesMapper.queryColthesByNum(orders.getOrdernum()));
        }
        PageInfo pageInfo = new PageInfo(laundryOrders);
        LayUIPage layUIPage = new LayUIPage();
        layUIPage.setCount(pageInfo.getTotal());
        layUIPage.setData(pageInfo.getList());
        return layUIPage;
    }

    /**
     * 客户端订单支付
     * @param laundryOrder
     * @return
     */
    public ResponseData updatePayment(LaundryOrder laundryOrder){
        return orderMapper.updateByPrimaryKeySelective(laundryOrder) > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("支付失败");
    }

    /**
     * 品种价格列表查询
     * @return
     */
    public ResponseData queryPriceList(){
        return new ResponseData(OptStatusCode.OPT_SUCCESS).setDataContext(orderMapper.queryPriceList());
    }

    /**
     * 客户洗衣订单添加
     * @param laundryOrder
     * @return
     */
    public ResponseData insertOrder(LaundryOrder laundryOrder){
        // 订单编号
        String randomFileName = RandUtils.getRandomFileName();
        laundryOrder.setOrdernum(randomFileName);
        //顾客id
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        laundryOrder.setLaundryuserid(Integer.valueOf(userid));
        // 衣服基本信息id
        List<LaundryClothes> clothesList = laundryOrder.getClothesList();
        BigDecimal totalPrice = new BigDecimal(0);
        int clothesResult = 0;
        for (LaundryClothes laundryClothes : clothesList) {
            totalPrice.add(laundryClothes.getPrice());
            laundryClothes.setOrdernum(randomFileName);
            clothesResult = clothesMapper.insert(laundryClothes);
        }
        if(clothesResult == 0){
            return ResponseFactory.createFaildResponse("洗衣服务下单失败");
        }
        //总价
        laundryOrder.setPrice(totalPrice);
        clothesResult = orderMapper.insertSelective(laundryOrder);
        return clothesResult > 0 ? ResponseFactory.createSuccessResponse() : ResponseFactory.createFaildResponse("洗衣服务下单失败");
    }
}
