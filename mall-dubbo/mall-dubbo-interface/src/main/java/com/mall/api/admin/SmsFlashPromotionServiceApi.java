package com.mall.api.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.pojo.SmsFlashPromotion;

import java.util.List;

/**
 * 秒杀活动列表
 */
public interface SmsFlashPromotionServiceApi {

    /**
     * 查询所有
     * @return
     */
    List<SmsFlashPromotion> list();

    /**
     * 根据活动名称分页查询
     */
    Page<SmsFlashPromotion> getPage(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 添加活动
     */
    void create(SmsFlashPromotion smsFlashPromotion);

    /**
     * 根据id删除活动
     */
    void deleteById(Long id);

    /**
     * 根据id修改上下线状态
     */
    void updateStatus(Long id,Integer status);

    /**
     * 编辑活动
     * @param smsFlashPromotion
     */
    void updateId(SmsFlashPromotion smsFlashPromotion);

    /**
     * 查询活动
     */
    SmsFlashPromotion selectById(Long id);
}
