package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.SmsFlashPromotionSessionServiceApi;
import com.mall.dubbo.mapper.SmsFlashPromotionSessionMapper;
import com.mall.pojo.SmsFlashPromotionSession;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsFlashPromotionSessionServiceApiImpl implements SmsFlashPromotionSessionServiceApi {

    @Autowired
    private SmsFlashPromotionSessionMapper smsFlashPromotionSessionMapper;

    /**
     * 获取全部场次
     */
    @Override
    public List<SmsFlashPromotionSession> findAll() {
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = smsFlashPromotionSessionMapper.selectList(null);

        return smsFlashPromotionSessions;
    }

    /**
     * 添加场次
     */
    @Override
    public void save(SmsFlashPromotionSession smsFlashPromotionSession) {
        smsFlashPromotionSessionMapper.insert(smsFlashPromotionSession);
    }

    /**
     * 删除场次
     */
    @Override
    public void deleteById(Long id) {
        smsFlashPromotionSessionMapper.deleteById(id);
    }

    /**
     * 修改
     */
    @Override
    public void updateStatus(SmsFlashPromotionSession smsFlashPromotionSession) {
        smsFlashPromotionSessionMapper.updateById(smsFlashPromotionSession);
    }

    @Override
    public List<SmsFlashPromotionSession> selectList(Integer flashPromotionId) {

        LambdaQueryWrapper<SmsFlashPromotionSession> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SmsFlashPromotionSession::getId,flashPromotionId);
        return smsFlashPromotionSessionMapper.selectList(wrapper);

    }
}
