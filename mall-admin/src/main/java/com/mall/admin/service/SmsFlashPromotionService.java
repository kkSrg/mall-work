package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsFlashPromotionServiceApi;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class SmsFlashPromotionService {

    @DubboReference
    private SmsFlashPromotionServiceApi smsFlashPromotionServiceApi;


    /**
     * 根据活动名称分页查询
     */
    public IPage<SmsFlashPromotion> getPage(Page page, String keyword) {

        IPage<SmsFlashPromotion> iPage = smsFlashPromotionServiceApi.getPage(page,keyword);

        return iPage;
    }
}
