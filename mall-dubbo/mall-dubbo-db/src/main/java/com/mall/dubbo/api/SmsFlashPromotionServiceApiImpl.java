package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.SmsFlashPromotionServiceApi;
import com.mall.dubbo.mapper.SmsFlashPromotionMapper;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsFlashPromotionServiceApiImpl  implements SmsFlashPromotionServiceApi {

    @Autowired
    private SmsFlashPromotionMapper smsFlashPromotionMapper;

    /**
     * 根据活动名称分页查询
     */
    @Override
    public List<SmsFlashPromotion> getPage(String keyword) {
        List<SmsFlashPromotion> smsFlashPromotions;
        LambdaQueryWrapper<SmsFlashPromotion> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isEmpty(keyword)){
            smsFlashPromotions = smsFlashPromotionMapper.selectList(null);
        }else {
            wrapper.like(SmsFlashPromotion::getTitle,keyword);
            smsFlashPromotions = smsFlashPromotionMapper.selectList(wrapper);
        }

        return smsFlashPromotions;
    }

    @Override
    public void create(SmsFlashPromotion smsFlashPromotion) {
        smsFlashPromotionMapper.insert(smsFlashPromotion);
    }

    @Override
    public void deleteById(Long id) {
        smsFlashPromotionMapper.deleteById(id);
    }

    @Override
    public void updateStatus(Long id,Integer status) {
        SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();
        smsFlashPromotion.setId(id);
        smsFlashPromotion.setStatus(status);
        smsFlashPromotionMapper.updateById(smsFlashPromotion);
    }

    @Override
    public void updateId(SmsFlashPromotion smsFlashPromotion) {
        smsFlashPromotionMapper.updateById(smsFlashPromotion);
    }

    @Override
    public SmsFlashPromotion selectById(Long id) {
        return smsFlashPromotionMapper.selectById(id);
    }
}
