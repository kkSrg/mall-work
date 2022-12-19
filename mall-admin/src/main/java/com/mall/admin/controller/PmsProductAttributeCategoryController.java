package com.mall.admin.controller;

import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsProductAttributeCategoryService;
import com.mall.vo.PmsProductAttributeCategoryVo;
import com.mall.pojo.PmsProductAttributeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService service;

    /**
     * 1.分页获取所有商品属性分类
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize){
        CommonPage<PmsProductAttributeCategory> result = service.list(pageNum,pageSize);
        return CommonResult.success(result);
    }

    /**
     * 2.获取所有商品属性分类及其下属性
     * @return
     */
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryVo>> listWithAttr(){
        List<PmsProductAttributeCategoryVo> result = service.listWithAttr();
        return CommonResult.success(result);
    }

    /**
     * 3.获取单个商品属性分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttributeCategory> getMsgById(@PathVariable Integer id){
        PmsProductAttributeCategory category = service.getMsgById(Convert.toLong(id));
        return CommonResult.success(category);
    }

    /**
     * 4.添加商品属性分类
     * @param name
     * @return
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestParam String name){
        service.create(name);
        return CommonResult.success("添加成功");
    }

    /**
     * 5.删除单个商品属性分类
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public CommonResult<String> deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return CommonResult.success("删除成功");
    }

    /**
     * 6.修改商品属性分类
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<String> updateById(@PathVariable Integer id,@RequestParam String name){
        service.updateById(id,name);
        return CommonResult.success("修改成功");
    }

}
