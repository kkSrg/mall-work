package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.SmsCouponHistory;

public interface SmsCouponHistoryServiceApi {
    IPage<SmsCouponHistory> getPage(IPage<SmsCouponHistory> page, Integer couponId, String orderSn, Integer useStatus);
}
