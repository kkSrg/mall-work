package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OrderOperateHistoryApi;
import com.mall.dubbo.mapper.OrderOperateHistoryMapper;
import com.mall.pojo.OmsOrderOperateHistory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class OrderOperateHistoryApiImpl implements OrderOperateHistoryApi {
    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;
    @Override
    public void insertList(List<OmsOrderOperateHistory> operateHistoryList) {
         orderOperateHistoryMapper.insert((OmsOrderOperateHistory) operateHistoryList);
    }

    @Override
    public void insert(OmsOrderOperateHistory history) {
        orderOperateHistoryMapper.insert(history);
    }

    @Override
    public List<OmsOrderOperateHistory> findById(Long orderId) {
        LambdaQueryWrapper<OmsOrderOperateHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OmsOrderOperateHistory::getOrderId,orderId);
        return orderOperateHistoryMapper.selectList(wrapper);
    }
}
