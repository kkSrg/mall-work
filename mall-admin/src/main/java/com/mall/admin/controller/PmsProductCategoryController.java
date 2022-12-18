package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.PmsProductCategoryService;
import com.mall.vo.PmsProductCategoryWithChildrenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;

    @GetMapping("list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren(){
        List<PmsProductCategoryWithChildrenItem> result = pmsProductCategoryService.listWithChildren();
        return CommonResult.success(result);
    }
}
