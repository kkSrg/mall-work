package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsCouponHistoryService;
import com.mall.pojo.SmsCouponHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService smsCouponHistoryService;


    /**
     * 根据优惠券id，使用状态，订单编号分页获取领取记录
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCouponHistory>> gerPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                              Integer couponId,String orderSn,Integer useStatus){

        CommonPage<SmsCouponHistory> couponHistoryCommonPage = smsCouponHistoryService.gerPage(pageNum, pageSize, couponId, orderSn, useStatus);

        return CommonResult.success(couponHistoryCommonPage);

    }

}
