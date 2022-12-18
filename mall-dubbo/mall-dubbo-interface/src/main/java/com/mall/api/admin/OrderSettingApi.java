package com.mall.api.admin;

import com.mall.pojo.OmsOrderSetting;

public interface OrderSettingApi {
    OmsOrderSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OmsOrderSetting orderSetting);
}
