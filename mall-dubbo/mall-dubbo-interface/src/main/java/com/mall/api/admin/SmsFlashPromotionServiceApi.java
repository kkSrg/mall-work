package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.pojo.SmsFlashPromotion;

/**
 * 秒杀活动列表
 */
public interface SmsFlashPromotionServiceApi {

    /**
     * 根据活动名称分页查询
     */
    IPage<SmsFlashPromotion> getPage(Page page, String keyword);
}
