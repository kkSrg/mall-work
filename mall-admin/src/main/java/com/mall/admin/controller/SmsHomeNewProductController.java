package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsHomeNewProductService;
import com.mall.pojo.SmsHomeNewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页新品
 *
 * @author 吴一飞
 * @date 2022/12/18 18:23
 */

@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService smsHomeNewProductService;


    /**
     * 分页查询首页新品
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeNewProduct>> smsHomeNewProductPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                             String productName, Integer recommendStatus) {
        CommonPage<SmsHomeNewProduct> page = smsHomeNewProductService.getPage(pageNum, pageSize, productName, recommendStatus);
        return CommonResult.success(page);
    }

    /**
     * 批量删除首页新品
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public CommonResult<Object> deletes(@RequestParam int[] ids) {
        smsHomeNewProductService.deletes(ids);
        return CommonResult.success(null);

    }

    /**
     * 批量修改首页新品状态
     *
     * @return
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult<Object> updates(@RequestParam Integer recommendStatus, @RequestParam int[] ids) {
        smsHomeNewProductService.updates(recommendStatus, ids);
        return CommonResult.success(null);
    }

    /**
     * 修改首页新品排序
     *
     * @param id
     * @param sort
     * @return
     */
    @PostMapping("/update/sort/{id}")
    public CommonResult<Object> updateSort(@PathVariable Long id, Integer sort) {
        smsHomeNewProductService.updateSort(id, sort);
        return CommonResult.success(null);
    }

    /**
     * 添加首页新品
     *
     * @author 吴一飞
     * @date 2022/12/18 20:25
     */
    @PostMapping("/create")
    public CommonResult<Object> save(@RequestBody List<SmsHomeNewProduct> homeNewProductList) {
        smsHomeNewProductService.saveList(homeNewProductList);
        return CommonResult.success(null);
    }

}
