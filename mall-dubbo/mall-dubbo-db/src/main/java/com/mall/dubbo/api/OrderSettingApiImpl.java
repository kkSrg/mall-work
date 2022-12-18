package com.mall.dubbo.api;

import com.mall.api.admin.OrderSettingApi;
import com.mall.dubbo.mapper.OrderSettingMapper;
import com.mall.pojo.OmsOrderSetting;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderSettingApiImpl implements OrderSettingApi {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Override
    public OmsOrderSetting selectByPrimaryKey(Long id) {
       return orderSettingMapper.selectById(id);
    }

    @Override
    public int updateByPrimaryKey(OmsOrderSetting orderSetting) {
     return    orderSettingMapper.updateById(orderSetting);
    }
}
