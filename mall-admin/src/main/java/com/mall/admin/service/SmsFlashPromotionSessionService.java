package com.mall.admin.service;

import com.mall.api.admin.SmsFlashPromotionSessionServiceApi;
import com.mall.pojo.SmsFlashPromotionSession;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 场次表
 * @author 吴一飞
 * @date 2022/12/16 22:05
 */

@Service
public class SmsFlashPromotionSessionService {
    @DubboReference
    private SmsFlashPromotionSessionServiceApi smsFlashPromotionSessionServiceApi;

    /**
     * 获取全部场次
     * @return
     */
    public List list() {
        List<SmsFlashPromotionSession> list = smsFlashPromotionSessionServiceApi.findAll();
        return list;
    }


    /**
     * 添加场次
     * @return
     */
    public void create(SmsFlashPromotionSession smsFlashPromotionSession) {
        smsFlashPromotionSessionServiceApi.save(smsFlashPromotionSession);
    }
    /**
     * 删除场次
     * @return
     */
    public void deleteById(Long id) {
        smsFlashPromotionSessionServiceApi.deleteById(id);
    }

    /**
     * 修改
     * @param smsFlashPromotionSession
     */
    public void updateStatus(SmsFlashPromotionSession smsFlashPromotionSession) {
        smsFlashPromotionSessionServiceApi.updateStatus(smsFlashPromotionSession);
    }

    public List<SmsFlashPromotionSession> selectList(Integer flashPromotionId) {
       return smsFlashPromotionSessionServiceApi.selectList(flashPromotionId);

    }
}
