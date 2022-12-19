package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OmsOrderItemApi;
import com.mall.dubbo.mapper.OmsOrderItemMapper;
import com.mall.pojo.OmsOrderItem;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class OmsOrderItemApiImpl implements OmsOrderItemApi {

    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    @Override
    public List<OmsOrderItem> findById(Long orderId) {
        LambdaQueryWrapper<OmsOrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OmsOrderItem::getOrderId,orderId);
        return omsOrderItemMapper.selectList(wrapper);
    }
}
