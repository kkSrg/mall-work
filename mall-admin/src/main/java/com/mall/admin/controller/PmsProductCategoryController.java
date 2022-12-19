package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsProductCategoryService;
import com.mall.pojo.PmsProductCategory;
import com.mall.vo.PmsProductCategoryWithChildrenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;


    /**
     * 1.分页查询商品分类
     * @return
     */
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> list(@PathVariable Integer parentId,
                                                             @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize){
        CommonPage<PmsProductCategory> result = pmsProductCategoryService.list(parentId,pageNum,pageSize);
        return CommonResult.success(result);
    }

    /**
     * 查询所有一级分类及子分类
     * @return
     */
    @GetMapping("list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren(){
        List<PmsProductCategoryWithChildrenItem> result = pmsProductCategoryService.listWithChildren();
        return CommonResult.success(result);
    }

    /**
     * 2.根据id获取商品分类
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getMsgById(@PathVariable Integer id){
        PmsProductCategory category = pmsProductCategoryService.getMsgById(id);
        return CommonResult.success(category);
    }

    /**
     * 3.添加商品分类
     * @return
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody PmsProductCategory productCategoryParam){
        pmsProductCategoryService.create(productCategoryParam);
        return CommonResult.success("添加成功");
    }

    /**
     * 4.删除商品分类
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public CommonResult<String> deleteById(@PathVariable Integer id){
        pmsProductCategoryService.deleteById(id);
        return CommonResult.success("删除成功");
    }

    /**
     * 5.修改商品分类
     * @param id
     * @param productCategoryParam
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<String> updateById(@PathVariable Integer id,@RequestBody PmsProductCategory productCategoryParam){
        pmsProductCategoryService.updateById(id,productCategoryParam);
        return CommonResult.success("修改成功");
    }

    /**
     * 6.修改导航栏显示状态
     * @param ids
     * @param navStatus
     * @return
     */
    @PostMapping("/update/navStatus")
    public CommonResult<String> updateNavStatus(@RequestParam List<Integer> ids,@RequestParam Integer navStatus){
        pmsProductCategoryService.updateNavStatus(ids,navStatus);
        return CommonResult.success("修改成功");
    }

    /**
     * 7.修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    @PostMapping("/update/showStatus")
    public CommonResult<String> updateShowStatus(@RequestParam List<Integer> ids,@RequestParam Integer showStatus){
        pmsProductCategoryService.updateShowStatus(ids,showStatus);
        return CommonResult.success("修改成功");
    }
}
