package com.mall.api.admin;

import com.mall.pojo.OmsOrderOperateHistory;

import java.util.List;

public interface OrderOperateHistoryApi {
    void insertList(List<OmsOrderOperateHistory> operateHistoryList);

    void insert(OmsOrderOperateHistory history);

    List<OmsOrderOperateHistory> findById(Long orderId);
}
