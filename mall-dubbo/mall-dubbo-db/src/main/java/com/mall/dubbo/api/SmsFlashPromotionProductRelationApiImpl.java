package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.api.admin.SmsFlashPromotionProductRelationApi;
import com.mall.dubbo.mapper.SmsFlashPromotionProductRelationMapper;
import com.mall.pojo.SmsFlashPromotionProductRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class SmsFlashPromotionProductRelationApiImpl implements SmsFlashPromotionProductRelationApi {

    @Autowired
    private SmsFlashPromotionProductRelationMapper smsFlashPromotionProductRelationMapper;

    @Override
    public IPage<SmsFlashPromotionProductRelation> getPage(IPage<SmsFlashPromotionProductRelation> page, Integer flashPromotionId, Integer flashPromotionSessionId) {


        LambdaQueryWrapper<SmsFlashPromotionProductRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(flashPromotionId!=null,SmsFlashPromotionProductRelation::getFlashPromotionId,flashPromotionId);
        wrapper.eq(flashPromotionSessionId!=null,SmsFlashPromotionProductRelation::getFlashPromotionSessionId,flashPromotionSessionId);
        smsFlashPromotionProductRelationMapper.selectPage(page,wrapper);

        return page;
    }

    @Override
    public void save(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation) {
        smsFlashPromotionProductRelationMapper.insert(smsFlashPromotionProductRelation);
    }

    @Override
    public void deleteById(Long id) {
        smsFlashPromotionProductRelationMapper.deleteById(id);
    }

    @Override
    public void update(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation) {
        smsFlashPromotionProductRelationMapper.updateById(smsFlashPromotionProductRelation);
    }

    @Override
    public SmsFlashPromotionProductRelation selectById(Long id) {
       return smsFlashPromotionProductRelationMapper.selectById(id);

    }
}
