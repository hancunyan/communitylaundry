package com.computer.community_laundry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 */
@Controller
public class HtmlController {

    // 服务端
    @RequestMapping("appointmentOrderManage")
    public String appointmentOrderManage(){
        return "pages/platform/laundry/order/appointmentOrderManage";
    }

    @RequestMapping("orderClothesForm")
    public String orderClothesForm(){
        return "pages/platform/laundry/order/orderClothesForm";
    }

    @RequestMapping("workingtechForm")
    public String workingtechForm(){
        return "pages/platform/laundry/order/workingtechForm";
    }

    @RequestMapping("distributionOrderManage")
    public String distributionOrderManage(){
        return "pages/platform/laundry/distribution/distributionOrderManage";
    }

    @RequestMapping("updoorOrderManage")
    public String updoorOrderManage(){
        return "pages/platform/laundry/updoor/updoorOrderManage";
    }

    @RequestMapping("laundyOrderManage")
    public String laundyOrderManage(){
        return "pages/platform/laundry/laundry/laundyOrderManage";
    }

    @RequestMapping("completeOrderManage")
    public String completeOrderManage(){
        return "pages/platform/laundry/complete/completeOrderManage";
    }

    @RequestMapping("complereOrderForm")
    public String complereOrderForm(){
        return "pages/platform/laundry/complete/complereOrderForm";
    }

    @RequestMapping("deliverOrderManage")
    public String deliverOrderManage(){
        return "pages/platform/laundry/deliver/deliverOrderManage";
    }

    @RequestMapping("deliverOrderForm")
    public String deliverOrderForm(){
        return "pages/platform/laundry/deliver/deliverOrderForm";
    }

    @RequestMapping("deviceManage")
    public String deviceManage(){
        return "pages/platform/device/deviceManage";
    }

    @RequestMapping("deviceForm")
    public String deviceForm(){
        return "pages/platform/device/deviceForm";
    }

    @RequestMapping("personalCenterManage")
    public String personalCenterManage(){
        return "pages/platform/users/personalCenterManage";
    }

    @RequestMapping("resetPasswordform")
    public String resetPasswordform(){
        return "pages/platform/users/resetPasswordform";
    }

    @RequestMapping("customerManage")
    public String customerManage(){
        return "pages/platform/users/customerManage";
    }

    @RequestMapping("customerForm")
    public String customerForm(){
        return "pages/platform/users/customerForm";
    }

    @RequestMapping("membershipCardManage")
    public String membershipCardManage(){
        return "pages/platform/membership/membershipCardManage";
    }

    @RequestMapping("membershipCardForm")
    public String membershipCardForm(){
        return "pages/platform/membership/membershipCardForm";
    }

    @RequestMapping("membershipLevelManage")
    public String membershipLevelManage(){
        return "pages/platform/membership/membershipLevelManage";
    }

    @RequestMapping("membershipLevelForm")
    public String membershipLevelForm(){
        return "pages/platform/membership/membershipLevelForm";
    }

    @RequestMapping("knowledgeManage")
    public String knowledgeManage(){
        return "pages/platform/knowledge/knowledgeManage";
    }

    @RequestMapping("knowledgeForm")
    public String knowledgeForm(){
        return "pages/platform/knowledge/knowledgeForm";
    }

    @RequestMapping("promotionManage")
    public String promotionManage(){
        return "pages/platform/knowledge/promotionManage";
    }

    @RequestMapping("promotionForm")
    public String promotionForm(){
        return "pages/platform/knowledge/promotionForm";
    }

    @RequestMapping("PriceManage")
    public String PriceManage(){
        return "pages/client/price/PriceManage";
    }

    @RequestMapping("priceForm")
    public String priceForm(){
        return "pages/client/price/priceForm";
    }
}
