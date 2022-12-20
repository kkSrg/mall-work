package com.mall.admin.controller;


import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsCouponService;
import com.mall.dto.SmsCouponDTO;
import com.mall.pojo.SmsCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param couponParam
     * @return
     */
    @PostMapping("/create")
    public CommonResult<Object> addSmsCoupon(@RequestBody SmsCouponDTO couponParam) {
        smsCouponService.addSmsCoupon(couponParam);

        return CommonResult.success(null);
    }

    /**
     * 删除优惠券
     */
    @PostMapping("/delete/{id}")
    public CommonResult<Object> deleteById(@PathVariable Long id) {
        smsCouponService.deleteById(id);

        return CommonResult.success(null);
    }


    /**
     * 添加优惠券
     *
     * @param couponParam
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<Object> updateSmsCoupon(@RequestBody SmsCouponDTO couponParam) {
        smsCouponService.updateSmsCoupon(couponParam);

        return CommonResult.success(null);
    }


    /**
     * 获取单个优惠券的详细信息
     *
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<SmsCouponDTO> smsCouponById(@PathVariable Long id) {
        SmsCouponDTO smsCouponDTO = smsCouponService.smsCouponById(id);

        return CommonResult.success(smsCouponDTO);
    }


}
