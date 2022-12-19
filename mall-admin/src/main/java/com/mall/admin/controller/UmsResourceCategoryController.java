package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.UmsResourceCategoryService;
import com.mall.pojo.UmsResource;
import com.mall.pojo.UmsResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加后台资源分类
     * @param umsResourceCategory
     * @return
     */
    @PostMapping("create")
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory){
        umsResourceCategoryService.create(umsResourceCategory);
        return CommonResult.success(1);
    }

    /**
     * 根据ID删除后台资源
     * @param id
     * @return
     */
    @PostMapping("delete/{id}")
    public CommonResult delete(@PathVariable Integer id){
        umsResourceCategoryService.delete(id);
        return CommonResult.success(1);
    }

    /**
     * 修改后台资源分类
     * @param id
     * @param umsResourceCategory
     * @return
     */
    @PostMapping("update/{id}")
    public CommonResult update(@PathVariable Integer id,@RequestBody UmsResourceCategory umsResourceCategory){
        umsResourceCategoryService.update(id,umsResourceCategory);
        return CommonResult.success(1);
    }
}
