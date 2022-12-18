package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsProductService;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.PmsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    /**
     * 查询商品
     * @param pmsProductListDto
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("list")
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductListDto pmsProductListDto,
                                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pagesize){
        CommonPage<PmsProduct> result = pmsProductService.getPage(pmsProductListDto,page,pagesize);
        return CommonResult.success(result);
    }
}
