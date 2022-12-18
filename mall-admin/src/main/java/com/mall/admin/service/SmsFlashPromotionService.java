package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.SmsFlashPromotionServiceApi;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SmsFlashPromotionService {

    @DubboReference
    private SmsFlashPromotionServiceApi smsFlashPromotionServiceApi;

    private Integer ONLINE = 1;

    /**
     * 根据活动名称分页查询
     */
    public CommonPage<SmsFlashPromotion> getPage(Integer pageNum, Integer pageSize, String keyword) {

        //查询分页
        Page<SmsFlashPromotion> page = smsFlashPromotionServiceApi.getPage(pageNum, pageSize, keyword);
        List<SmsFlashPromotion> list = page.getRecords();

        //分页计算
        CommonPage<SmsFlashPromotion> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotal(Convert.toInt(page.getTotal()));
        double ceil = Math.ceil(Convert.toDouble(page.getTotal()) / Convert.toDouble(pageSize));
        result.setTotalPage(Convert.toInt(ceil));

        result.setList(list);
        return result;
    }

    /**
     * 添加活动
     *
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
    public void updateStatus(Long id, Integer status) {

        smsFlashPromotionServiceApi.updateStatus(id, status);
    }

    /**
     * 编辑活动
     *
     * @param smsFlashPromotion
     */
    public void updateId(SmsFlashPromotion smsFlashPromotion) {
        smsFlashPromotionServiceApi.updateId(smsFlashPromotion);
    }

    /**
     * 获取活动详情
     * @param id
     * @return
     */
    public SmsFlashPromotion eventDetails(Long id) {
        SmsFlashPromotion smsFlashPromotion = smsFlashPromotionServiceApi.selectById(id);
        return smsFlashPromotion;
    }
}
