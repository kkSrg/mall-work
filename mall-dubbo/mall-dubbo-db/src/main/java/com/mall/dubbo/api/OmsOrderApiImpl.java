package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OmsOrderApi;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.dubbo.mapper.OmsOrderMapper;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderExample;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class OmsOrderApiImpl implements OmsOrderApi {
    @Autowired
    private OmsOrderMapper omsOrderMapper;


    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        int count=omsOrderMapper.selectCount((Wrapper<OmsOrder>) deliveryParamList);
        return count;
    }

    @Override
    public int updateByExampleSelective(OmsOrder record, OmsOrderExample example) {
        int update = omsOrderMapper.update(record,null);
        return update;
    }

    @Override
    public List<OmsOrder> getList(OmsOrderQueryParam queryParam) {
        List<OmsOrder> omsOrders  ;
        LambdaQueryWrapper<OmsOrder> wrapper = new LambdaQueryWrapper<>();
        omsOrders = omsOrderMapper.selectList(wrapper);
        return omsOrders;
    }
}
