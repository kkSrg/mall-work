package com.mall.dubbo.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OmsOrderApi;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.dubbo.mapper.OmsOrderMapper;
import com.mall.pojo.OmsOrder;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class OmsOrderApiImpl implements OmsOrderApi {
    @Autowired
    private OmsOrderMapper omsOrderMapper;


    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        return omsOrderMapper.selectCount((Wrapper<OmsOrder>) deliveryParamList);
    }


    @Override
    public List<OmsOrder> getList(OmsOrderQueryParam queryParam) {
        List<OmsOrder> omsOrders;
        LambdaQueryWrapper<OmsOrder> wrapper = new LambdaQueryWrapper<>();
        String orderSn = queryParam.getOrderSn();
        wrapper.like(OmsOrder::getOrderSn, orderSn);
        omsOrders = omsOrderMapper.selectList(wrapper);
        return omsOrders;
    }

    @Override
    public int deleteByExampleSelective(OmsOrder record, List<Long> ids) {
        List<Long> collect = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        return omsOrderMapper.deleteBatchIds(collect);
    }

    @Override
    public int updateDelivery(OmsOrder record, List<Long> ids) {
        LambdaQueryWrapper<OmsOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(OmsOrder::getId, ids);
        return omsOrderMapper.update(record, queryWrapper);


    }

    @Override
    public OmsOrderDetail gedetail(Long id) {
        return (OmsOrderDetail) omsOrderMapper.selectById(id);

    }

    @Override
    public int updateByPrimaryKeySelective(OmsOrder order) {

        return omsOrderMapper.updateById(order);
    }

}
