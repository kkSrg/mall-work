package com.mall.admin.controller;


import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsCouponService;
import com.mall.pojo.SmsCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券表
 *
 * @author 吴一飞
 * @date 2022/12/17 15:05
 */

@RestController
@RequestMapping("/coupon")
public class SmsCouponController {

    @Autowired
    private SmsCouponService smsCouponService;


    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCoupon>> smsCouponPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             String name, Integer type) {
        CommonPage<SmsCoupon> page = smsCouponService.smsCouponPage(pageNum, pageSize, name, type);


        return CommonResult.success(page);
    }


    /**
     * 添加优惠券
     * @return
     */
    public CommonResult<Object> addSmsCoupon(){
       return null;
    }


}
