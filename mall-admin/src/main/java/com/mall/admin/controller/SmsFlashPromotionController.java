package com.mall.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.service.SmsFlashPromotionService;
import com.mall.pojo.SmsFlashPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀活动列表
 *
 * @author 吴一飞
 * @date 2022/12/16 15:10
 */
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashPromotionService smsFlashPromotionService;


    /**
     * 根据活动名称分页查询
     *
     * @author 吴一飞
     * @date 2022/12/16 15:20
     */
    @GetMapping("/list")
    public ResponseEntity<Object> list(Page page, String keyword) {
        //调用service获取分页
        IPage<SmsFlashPromotion> iPage = smsFlashPromotionService.getPage(page,keyword);

        return ResponseEntity.ok(iPage);
    }

}
