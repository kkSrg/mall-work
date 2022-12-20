package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.PmsSkuStockService;
import com.mall.pojo.PmsSkuStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {

    @Autowired
    private PmsSkuStockService pmsSkuStockService;

    /**
     * 1.批量更新库存信息
     * @return
     */
    @PostMapping("/update/{pid}")
    public CommonResult<Integer> updateByPid(@PathVariable Integer pid, @RequestBody List<PmsSkuStock> skuStockList){
        Integer count = pmsSkuStockService.updateByPid(pid,skuStockList);
        return CommonResult.success(count);
    }

    /**
     * 2.根据商品id及sku编码模糊搜索sku库存
     * @return
     */
    @GetMapping("/{pid}")
    public CommonResult<List<PmsSkuStock>> selectSku(@PathVariable Integer pid,String keyword){
        if (keyword==null) {
            List<PmsSkuStock> result = pmsSkuStockService.selectSku(pid);
            return CommonResult.success(result);
        }
        List<PmsSkuStock> result = pmsSkuStockService.selectSkuList(pid,keyword);
        return CommonResult.success(result);
    }
}
