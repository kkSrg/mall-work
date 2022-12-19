package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.UmsResourceCategoryService;
import com.mall.pojo.UmsResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("resourceCategory")
public class UmsResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService umsResourceCategoryService;

    /**
     * 查询所有后台资源分类
     * @return
     */
    @GetMapping("listAll")
    public CommonResult<List<UmsResourceCategory>> listAll(){
        List<UmsResourceCategory> result = umsResourceCategoryService.listAll();
        return CommonResult.success(result);
    }
}
