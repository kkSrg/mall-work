package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.SmsFlashPromotionServiceApi;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsFlashPromotionService {

    @DubboReference
    private SmsFlashPromotionServiceApi smsFlashPromotionServiceApi;


    /**
     * 根据活动名称分页查询
     */
    public CommonPage<SmsFlashPromotion> getPage(Integer pageNum, Integer pageSize, String keyword) {
        CommonPage<SmsFlashPromotion> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotalPage(1);
        result.setTotal(10);

        List<SmsFlashPromotion> list = smsFlashPromotionServiceApi.getPage(keyword);
        result.setList(list);
        return result;
    }

    /**
     * 添加活动
     * @param smsFlashPromotion
     */
    public void create(SmsFlashPromotion smsFlashPromotion) {

        smsFlashPromotionServiceApi.create(smsFlashPromotion);
    }

    /**
     * 根据id删除活动
     */
    public void deleteById(Long id) {
        smsFlashPromotionServiceApi.deleteById(id);
    }

    /**
     * 根据id修改上下线状态
     */
    public void updateStatus(Long id,Integer status) {

        smsFlashPromotionServiceApi.updateStatus(id,status);
    }

    /**
     * 编辑活动
     * @param smsFlashPromotion
     */
    public void updateId(SmsFlashPromotion smsFlashPromotion) {
        smsFlashPromotionServiceApi.updateId(smsFlashPromotion);
    }

    public SmsFlashPromotion eventDetails(Long id) {
        SmsFlashPromotion smsFlashPromotion = smsFlashPromotionServiceApi.selectById(id);
        return smsFlashPromotion;
    }
}
