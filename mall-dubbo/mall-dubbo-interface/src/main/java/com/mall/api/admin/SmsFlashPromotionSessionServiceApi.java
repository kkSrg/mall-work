package com.mall.api.admin;

import com.mall.pojo.SmsFlashPromotionSession;

import java.util.List;

public interface SmsFlashPromotionSessionServiceApi {
    /**
     * 获取全部场次
     */
    List<SmsFlashPromotionSession> findAll();

    /**
     * 添加场次
     */
    void save(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 删除场次
     */
    void deleteById(Long id);

    /**
     * 修改
     */
    void updateStatus(SmsFlashPromotionSession smsFlashPromotionSession);


    List<SmsFlashPromotionSession> selectList(Integer flashPromotionId);
}
