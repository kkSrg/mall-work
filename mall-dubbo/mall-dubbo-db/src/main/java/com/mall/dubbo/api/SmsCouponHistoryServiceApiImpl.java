package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.api.admin.SmsCouponHistoryServiceApi;
import com.mall.dubbo.mapper.SmsCouponHistoryMapper;
import com.mall.pojo.SmsCouponHistory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class SmsCouponHistoryServiceApiImpl implements SmsCouponHistoryServiceApi {

    @Autowired
    private SmsCouponHistoryMapper smsCouponHistoryMapper;

    @Override
    public IPage<SmsCouponHistory> getPage(IPage<SmsCouponHistory> page, Integer couponId, String orderSn, Integer useStatus) {


        LambdaQueryWrapper<SmsCouponHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(couponId != null, SmsCouponHistory::getCouponId, couponId);
        wrapper.like(orderSn != null, SmsCouponHistory::getOrderSn, orderSn);
        wrapper.like(useStatus != null, SmsCouponHistory::getUseStatus, useStatus);
        smsCouponHistoryMapper.selectPage(page, wrapper);
        return page;
    }
}
