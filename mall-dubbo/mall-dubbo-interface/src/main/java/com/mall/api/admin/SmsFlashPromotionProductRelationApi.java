package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.SmsFlashPromotionProductRelation;

public interface SmsFlashPromotionProductRelationApi {
    IPage<SmsFlashPromotionProductRelation> getPage(IPage<SmsFlashPromotionProductRelation> page, Integer flashPromotionId, Integer flashPromotionSessionId);

    void save(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation);

    void deleteById(Long id);

    void update(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation);

    SmsFlashPromotionProductRelation selectById(Long id);
}
