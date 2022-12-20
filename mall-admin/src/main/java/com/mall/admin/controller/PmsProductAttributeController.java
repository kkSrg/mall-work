package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsProductAttributeService;
import com.mall.pojo.PmsProductAttribute;
import com.mall.vo.AttributeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService pmsProductAttributeService;

    /**
     * 1.根据分类查询属性列表或参数列表
     * @return
     */
    @GetMapping("/list/{cid}")
    public CommonResult list(@PathVariable Integer cid, Integer type,
                                                               Integer pageNum,
                                                               Integer pageSize){
        if (pageNum!=null && pageSize!=null){
            CommonPage<PmsProductAttribute> result = pmsProductAttributeService.list(cid,type,pageNum,pageSize);
            return CommonResult.success(result);
        }
        List<PmsProductAttribute> result = pmsProductAttributeService.listOther(cid,type);
        return CommonResult.success(result);
    }

    /**
     * 1.根据分类查询属性列表或参数列表
     * @return
     */
    /*@GetMapping(value = "/list/{cid}",params = {"cid","type"})
    public CommonResult<List<PmsProductAttribute>> listOther(@PathVariable Integer cid, Integer type){
        List<PmsProductAttribute> result = pmsProductAttributeService.listOther(cid,type);
        return CommonResult.success(result);
    }*/


    /**
     * 2.根据商品分类的id获取商品属性及属性分类
     * @return
     */
    @GetMapping("/attrInfo/{productCategoryId}")
    public CommonResult<List<AttributeVo>> attrInfo(@PathVariable Integer productCategoryId){
        List<AttributeVo> result = pmsProductAttributeService.attrInfo(productCategoryId);
        return CommonResult.success(result);
    }

    /**
     * 3.查询单个商品属性
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getMsgById(@PathVariable Integer id){
        PmsProductAttribute attribute = pmsProductAttributeService.getMsgById(id);
        return CommonResult.success(attribute);
    }

    /**
     * 4.添加商品属性信息
     * @return
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody PmsProductAttribute productAttributeParam){
        pmsProductAttributeService.create(productAttributeParam);
        return CommonResult.success("添加成功");
    }

    /**
     * 5.批量删除商品属性
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public CommonResult<String> delete(@RequestParam List<Integer> ids){
        pmsProductAttributeService.delete(ids);
        return CommonResult.success("删除成功");
    }

    /**
     * 6.修改商品属性信息
     * @param id
     * @param productAttributeParam
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<String> update(@PathVariable Integer id,@RequestBody PmsProductAttribute productAttributeParam){
        pmsProductAttributeService.update(id,productAttributeParam);
        return CommonResult.success("修改成功");
    }

}
