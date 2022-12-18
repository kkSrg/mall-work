package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsHomeBrandService;
import com.mall.dto.UpdateRecommendStatusDTO;
import com.mall.pojo.SmsHomeBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推荐品牌表
 *
 * @author 吴一飞
 * @date 2022/12/17 19:48
 */
@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService smsHomeBrandService;

    /**
     * 分页查询推荐品牌
     *
     * @param pageNum
     * @param pageSize
     * @param brandName
     * @param recommendStatus
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeBrand>> smsHomeBrandPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                   String brandName, Integer recommendStatus) {
        CommonPage<SmsHomeBrand> page = smsHomeBrandService.smsHomeBrandPage(pageNum, pageSize, brandName, recommendStatus);

        return CommonResult.success(page);
    }


    /**
     * 添加首页推荐品牌
     *
     * @param smsHomeBrand
     * @return
     */
    @PostMapping("/create")
    public CommonResult<Object> addSmsHomeBrand(@RequestBody SmsHomeBrand smsHomeBrand) {
        smsHomeBrandService.addSmsHomeBrand(smsHomeBrand);
        return CommonResult.success(null);
    }

    /**
     * 批量删除推荐品牌
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public CommonResult<Object> deletes(@RequestParam(value = "ids")  int[] ids) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        smsHomeBrandService.deletes(list);
        return CommonResult.success(null);
    }


    /**
     * 批量修改推荐品牌状态
     *
     * @param
     * @return
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult<Object> recommendStatus(@RequestParam Integer recommendStatus,@RequestParam int[] ids) {
        UpdateRecommendStatusDTO updateRecommendStatusDTO = new UpdateRecommendStatusDTO();
        updateRecommendStatusDTO.setRecommendStatus(recommendStatus);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        updateRecommendStatusDTO.setIds(list);
        smsHomeBrandService.recommendStatus(updateRecommendStatusDTO);
        return CommonResult.success(null);
    }

    /**
     * 修改排序
     *
     * @param
     * @return
     */
    @PostMapping("/update/sort/{id}")
    public CommonResult<Object> updateSort(@PathVariable Long id, Integer sort) {
        smsHomeBrandService.updateSort(id, sort);
        return CommonResult.success(null);
    }

}
