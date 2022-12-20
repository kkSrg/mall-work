package com.mall.api.admin;

import com.mall.pojo.OmsOrderItem;

import java.util.List;

public interface OmsOrderItemApi {
    List<OmsOrderItem> findById(Long orderId);
}
