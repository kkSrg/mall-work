package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mall.api.admin.OrderReturnReasonApi;
import com.mall.dubbo.mapper.OrderReturnReasonMapper;
import com.mall.pojo.OmsOrderReturnReason;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class OrderReturnReasonImpl implements OrderReturnReasonApi {
    @Autowired
    private OrderReturnReasonMapper orderReturnReasonMapper;
    @Override
    public List<OmsOrderReturnReason> getList() {
        return orderReturnReasonMapper.selectList(null);
    }

    @Override
    public int insert(OmsOrderReturnReason returnReason) {
        return orderReturnReasonMapper.insert(returnReason);
    }

    @Override
    public int updateByPrimaryKey(OmsOrderReturnReason returnReason) {
        return orderReturnReasonMapper.updateById(returnReason);
    }

    @Override
    public OmsOrderReturnReason selectByPrimaryKey(Long id) {
        return orderReturnReasonMapper.selectById(id);
    }

    @Override
    public int updateStatus(List<Long> ids,Integer status) {
        OmsOrderReturnReason omsOrderReturnReason=new OmsOrderReturnReason();
        LambdaUpdateWrapper<OmsOrderReturnReason> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(OmsOrderReturnReason::getId,ids).set(OmsOrderReturnReason::getStatus,status);;
        return orderReturnReasonMapper.update(omsOrderReturnReason,wrapper);
    }

    @Override
    public int delete(List<Long> ids) {
        return orderReturnReasonMapper.deleteBatchIds(ids);
    }
}
