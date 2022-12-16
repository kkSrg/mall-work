package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsFlashPromotionServiceApi;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class SmsFlashPromotionServiceApiImpl implements SmsFlashPromotionServiceApi {

    /**
     * 根据活动名称分页查询
     */
    @Override
    public IPage<SmsFlashPromotion> getPage(Page page, String keyword) {
        return null;
    }
}
