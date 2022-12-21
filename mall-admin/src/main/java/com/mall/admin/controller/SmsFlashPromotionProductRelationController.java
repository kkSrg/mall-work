package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsFlashPromotionProductRelationService;
import com.mall.pojo.SmsFlashPromotion;
import com.mall.pojo.SmsFlashPromotionProductRelation;
import com.mall.pojo.SmsFlashPromotionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService smsFlashPromotionProductRelationService;

    /**
     * 分页查询不同场次关联及商品信息
     *
     * @author 吴一飞
     * @date 2022/12/16 15:20
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionProductRelation>> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                                           @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize,
                                                                           Integer flashPromotionId, Integer flashPromotionSessionId) {
        //调用service获取分页
        CommonPage<SmsFlashPromotionProductRelation> page = smsFlashPromotionProductRelationService.getPage(pageNum,pageSize,flashPromotionId,flashPromotionSessionId);

        return CommonResult.success(page);
    }



    /**
     * 批量选择商品添加关联
     * @param
     * @return
     */
    @PostMapping("/create")
    public CommonResult<Object> create(@RequestBody List<SmsFlashPromotionProductRelation> relationList){
        smsFlashPromotionProductRelationService.create(relationList);
        return CommonResult.success(null);
    }

    /**
     * 删除关联
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public CommonResult<Object> delete(@PathVariable Long id){
        smsFlashPromotionProductRelationService.deleteById(id);
        return CommonResult.success(null);
    }

    /**
     * 修改关联信息
     * @param id
     * @param
     * @return
     */
    @PostMapping("/update/{id}")
    private CommonResult<Object> updateById(@PathVariable Long id,@RequestBody SmsFlashPromotionProductRelation smsFlashPromotionProductRelation){
        smsFlashPromotionProductRelation.setId(id);
        smsFlashPromotionProductRelationService.update(smsFlashPromotionProductRelation);
        return CommonResult.success(null);
    }


    @GetMapping("/{id}")
    public CommonResult<SmsFlashPromotionProductRelation> selectById(@PathVariable Long id){
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = smsFlashPromotionProductRelationService.selectById(id);
        return CommonResult.success(smsFlashPromotionProductRelation);
    }

}
