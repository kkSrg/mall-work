package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * 查询所有
     * @return
     */
    @Override
    public List<SmsFlashPromotion> list() {
        List<SmsFlashPromotion> list = smsFlashPromotionMapper.selectList(null);
        return list;
    }

    /**
     * 根据活动名称分页查询
     */
    @Override
    public Page<SmsFlashPromotion> getPage(Integer pageNum, Integer pageSize,String keyword) {
        LambdaQueryWrapper<SmsFlashPromotion> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SmsFlashPromotion::getStatus);
        Page<SmsFlashPromotion> page = new Page<>(pageNum,pageSize);
        if (StrUtil.isEmpty(keyword)){
            smsFlashPromotionMapper.selectPage(page,wrapper);
        }else {
            wrapper.like(SmsFlashPromotion::getTitle,keyword);
            smsFlashPromotionMapper.selectPage(page, wrapper);
        }
        return page;
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
    /**
     * 查询活动
     */
    @Override
    public SmsFlashPromotion selectById(Long id) {
        return smsFlashPromotionMapper.selectById(id);
    }
}
