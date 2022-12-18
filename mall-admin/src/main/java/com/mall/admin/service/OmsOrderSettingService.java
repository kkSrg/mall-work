package com.mall.admin.service;

import com.mall.api.admin.OrderSettingApi;
import com.mall.pojo.OmsOrderSetting;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class OmsOrderSettingService {
    @DubboReference
    private OrderSettingApi orderSettingApi;
    /**
     * 获取指定订单设置
     * @param id
     * @return
     */
    public OmsOrderSetting getItem(Long id) {
        return orderSettingApi.selectByPrimaryKey(id);
    }
    /**
     * 修改指定订单设置
     * @param id
     * @param orderSetting
     * @return
     */
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingApi.updateByPrimaryKey(orderSetting);
    }
}
