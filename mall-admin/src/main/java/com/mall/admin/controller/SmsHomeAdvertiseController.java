package com.mall.admin.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsHomeAdvertiseService;
import com.mall.pojo.SmsHomeAdvertise;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService SmsHomeAdvertiseService;

    @Autowired
    private OssController ossController;

    /**
     * 新增广告
     */
    @PostMapping("/advertise/create")
    public CommonResult create(@RequestBody SmsHomeAdvertise smsHomeAdvertise) {

        SmsHomeAdvertiseService.save(smsHomeAdvertise);
        return CommonResult.success(null);
    }

    /**
     * 删除广告
     */
    @PostMapping("/advertise/delete")
    //用集合存ids
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids) {
        SmsHomeAdvertiseService.removeByIds(ids);
        return CommonResult.success(null);
    }

    /**
     * 分页查询
     */
    @GetMapping("/advertise/list")
    public CommonResult<CommonPage<SmsHomeAdvertise>> page(@RequestParam(value = "endTime",required = false) String endTime,
                             @RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "type",required = false)Integer type,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum

    ) {


        CommonPage<SmsHomeAdvertise> result =SmsHomeAdvertiseService.page(endTime, name,type,pageSize, pageNum);
        return CommonResult.success(result);
    }

    /**
     * 修改上下线状态
     */
    @PostMapping("/advertise/update/status/{id}")
    public CommonResult status(@PathVariable Integer id,Integer status){
        SmsHomeAdvertise advertise = SmsHomeAdvertiseService.getById(id);
        advertise.setStatus(status);
        SmsHomeAdvertiseService.updateById(advertise);
        return CommonResult.success(null);
    }

    /**
     * 根据ID查询单个(修改回显数据)
     */
    @GetMapping("/advertise/{id}")
    public CommonResult findById(@PathVariable Integer id){
        SmsHomeAdvertise advertise = SmsHomeAdvertiseService.getById(id);
        return CommonResult.success(advertise);
    }


    /**
     * 修改数据信息
     */
    @PostMapping("/advertise/update/{id}")
    public CommonResult update(@PathVariable Integer id,@RequestBody SmsHomeAdvertise smsHomeAdvertise){
        SmsHomeAdvertiseService.updateById(smsHomeAdvertise);
        return CommonResult.success(null);
    }

}
