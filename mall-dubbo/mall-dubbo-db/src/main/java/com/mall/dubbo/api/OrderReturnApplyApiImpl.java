package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OrderReturnApplyApi;
import com.mall.dto.OmsOrderReturnApplyResult;
import com.mall.dto.OmsReturnApplyQueryParam;
import com.mall.dubbo.mapper.OrderReturnApplyMapper;
import com.mall.pojo.OmsOrderReturnApply;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class OrderReturnApplyApiImpl implements OrderReturnApplyApi {
    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;

    @Override
    public List<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam queryParam) {

        LambdaQueryWrapper<OmsOrderReturnApply> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryParam.getId() != null, OmsOrderReturnApply::getId, queryParam.getId());
        wrapper.like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnName, queryParam.getReceiverKeyword()).or()
                .like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnPhone, queryParam.getReceiverKeyword());
        wrapper.like(queryParam.getStatus()!=null,OmsOrderReturnApply::getStatus, queryParam.getStatus());
        wrapper.like(queryParam.getHandleMan()!=null, OmsOrderReturnApply::getHandleMan, queryParam.getHandleMan());
        wrapper.like(queryParam.getHandleTime()!=null, OmsOrderReturnApply::getHandleTime, queryParam.getHandleTime());
        wrapper.like(queryParam.getCreateTime()!=null, OmsOrderReturnApply::getCreateTime, queryParam.getCreateTime());
        return orderReturnApplyMapper.selectList(wrapper);
    }

    @Override
    public OmsOrderReturnApplyResult getDetail(Long id) {
        return null;
    }

    @Override
    public int delete(List<Long> ids) {
        return orderReturnApplyMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateByPrimaryKeySelective(OmsOrderReturnApply returnApply) {
        return orderReturnApplyMapper.updateById(returnApply);
    }
}
