package com.mall.admin.controller;


import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsHomeRecommendProductService;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService smsHomeRecommendProductService;

    //分页查询
    @GetMapping("/recommendProduct/list")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName",required = false)String productName,
                                     @RequestParam(value = "recommendStatus",required = false)Integer recommendStatus,
                                     @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
        CommonPage<SmsHomeRecommendProduct>  result = smsHomeRecommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(result);
    }

    //批量删除
    @PostMapping("/recommendProduct/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = smsHomeRecommendProductService.delete(ids);
        if (count>0){
            return CommonResult.success("删除成功");
        }
        return CommonResult.error("删除失败");
    }

    //批量修改推荐状态
    @PostMapping("/recommendProduct/update/recommendStatus")
    public CommonResult updateRecommend(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus){
        int count = smsHomeRecommendProductService.updateCommend(ids,recommendStatus);

        if (count>0){
            return CommonResult.success("修改成功");
        }
        return CommonResult.success("修改失败");
    }

    //修改推荐排序
    @PostMapping("/recommendProduct/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Integer id ,Integer sort){
        smsHomeRecommendProductService.updateSort(id,sort);
        return CommonResult.success(null);
    }

    //首页添加商品
    @PostMapping("/recommendProduct/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendProduct> smsHomeRecommendProductList){
        smsHomeRecommendProductService.create(smsHomeRecommendProductList);
        return CommonResult.success(null);
    }
}
