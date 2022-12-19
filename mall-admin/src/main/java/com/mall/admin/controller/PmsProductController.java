package com.mall.admin.controller;

import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsProductService;
import com.mall.dto.PmsProductDto;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.PmsProduct;
import com.mall.vo.PmsProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    /**
     * 1.根据商品名称或货号模糊查询
     * @return
     */
    @GetMapping("/simpleList")
    public CommonResult<List<PmsProduct>> simpleList(@RequestParam String keyword){
        List<PmsProduct> result = pmsProductService.simpleList(keyword);
        return CommonResult.success(result);
    }

    /**
     * 2.根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    @GetMapping("/updateInfo/{id}")
    public CommonResult<PmsProductVo> updateInfoById(@PathVariable Integer id){
        PmsProductVo result = pmsProductService.updateInfoById(Convert.toLong(id));
        return CommonResult.success(result);
    }

    /**
     * 3.创建商品
     * @param productParam
     * @return
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody PmsProductDto productParam){
        pmsProductService.create(productParam);
        return CommonResult.success("创建成功");
    }

    /**
     * 4.更新商品
     * @param id
     * @param productParam
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<String> updateById(@PathVariable Integer id,@RequestBody PmsProductDto productParam){
        pmsProductService.updateById(Convert.toLong(id),productParam);
        return CommonResult.success("更新成功");
    }

    /**
     * 5.批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return
     */
    @PostMapping("/update/deleteStatus")
    public CommonResult<String> deleteStatus(@RequestParam List<Integer> ids,@RequestParam Integer deleteStatus){
        pmsProductService.deleteStatus(ids,deleteStatus);
        return CommonResult.success("修改成功");
    }

    /**
     * 6.批量设为新品
     * @param ids
     * @param newStatus
     * @return
     */
    @PostMapping("/update/newStatus")
    public CommonResult<String> newStatus(@RequestParam List<Integer> ids,@RequestParam Integer newStatus){
        pmsProductService.newStatus(ids,newStatus);
        return CommonResult.success("设定成功");
    }

    /**
     * 7.批量上下架商品
     * @param ids
     * @param publishStatus
     * @return
     */
    @PostMapping("/update/publishStatus")
    public CommonResult<String> publishStatus(@RequestParam List<Integer> ids,@RequestParam Integer publishStatus){
        pmsProductService.publishStatus(ids,publishStatus);
        return CommonResult.success("操作成功");
    }

    /**
     * 8.批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult<String> recommendStatus(@RequestParam List<Integer> ids,@RequestParam Integer recommendStatus){
        pmsProductService.recommendStatus(ids,recommendStatus);
        return CommonResult.success("操作成功");
    }

    /**
     * 9.批量修改审核状态
     * @param detail
     * @param ids
     * @param verifyStatus
     * @return
     */
    @PostMapping("/update/verifyStatus")
    public CommonResult<String> verifyStatus(@RequestParam String detail,@RequestParam List<Integer> ids,@RequestParam Integer verifyStatus){
        pmsProductService.verifyStatus(detail,ids,verifyStatus);
        return CommonResult.success("修改成功");
    }

}
