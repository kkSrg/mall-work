package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.OmsOrderSettingService;
import com.mall.pojo.OmsOrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService orderSettingService;

    /**
     * 获取指定订单设置
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderSetting> getItem(@PathVariable Long id) {
        OmsOrderSetting orderSetting = orderSettingService.getItem(id);
        return CommonResult.success(orderSetting);
    }

    /**
     * 修改指定订单设置
     * @param id
     * @param orderSetting
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting) {
        int count = orderSettingService.update(id,orderSetting);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }

}
