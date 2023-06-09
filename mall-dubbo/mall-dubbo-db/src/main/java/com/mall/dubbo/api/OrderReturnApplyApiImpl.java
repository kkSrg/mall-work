package com.mall.dubbo.api;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.OrderReturnApplyApi;
import com.mall.dto.OmsOrderReturnApplyResult;
import com.mall.dto.OmsReturnApplyQueryParam;
import com.mall.dubbo.mapper.OrderReturnApplyMapper;
import com.mall.pojo.OmsOrder;
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

     /*   LambdaQueryWrapper<OmsOrderReturnApply> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryParam.getId() != null, OmsOrderReturnApply::getId, queryParam.getId());
        wrapper.like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnName, queryParam.getReceiverKeyword()).or()
                .like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnPhone, queryParam.getReceiverKeyword());
        wrapper.like(queryParam.getStatus()!=null,OmsOrderReturnApply::getStatus, queryParam.getStatus());
        wrapper.like(queryParam.getHandleMan()!=null, OmsOrderReturnApply::getHandleMan, queryParam.getHandleMan());
        wrapper.like(queryParam.getHandleTime()!=null, OmsOrderReturnApply::getHandleTime, queryParam.getHandleTime());
        wrapper.like(queryParam.getCreateTime()!=null, OmsOrderReturnApply::getCreateTime, queryParam.getCreateTime());
        return orderReturnApplyMapper.selectList(wrapper);*/
        return null;
    }

    @Override
    public CommonPage<OmsOrderReturnApply> getPage(OmsReturnApplyQueryParam queryParam, Integer pageNum, Integer pageSize) {
        IPage<OmsOrderReturnApply> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<OmsOrderReturnApply> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryParam.getId() != null, OmsOrderReturnApply::getId, queryParam.getId());
        wrapper.like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnName, queryParam.getReceiverKeyword()).or()
                .like(queryParam.getReceiverKeyword() != null, OmsOrderReturnApply::getReturnPhone, queryParam.getReceiverKeyword());
        wrapper.like(queryParam.getStatus()!=null,OmsOrderReturnApply::getStatus, queryParam.getStatus());
        wrapper.like(queryParam.getHandleMan()!=null, OmsOrderReturnApply::getHandleMan, queryParam.getHandleMan());
        wrapper.like(queryParam.getHandleTime()!=null, OmsOrderReturnApply::getHandleTime, queryParam.getHandleTime());
        wrapper.like(queryParam.getCreateTime()!=null, OmsOrderReturnApply::getCreateTime, queryParam.getCreateTime());
       orderReturnApplyMapper.selectPage(page, wrapper);

        CommonPage<OmsOrderReturnApply> pageResult = new CommonPage<>();
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage((int)page.getPages());
        pageResult.setTotal((int)page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;
    }


    @Override
    public OmsOrderReturnApplyResult getDetail(Long id) {
        OmsOrderReturnApplyResult omsOrderReturnApplyResult=new OmsOrderReturnApplyResult();
        OmsOrderReturnApply omsOrderReturnApply = orderReturnApplyMapper.selectById(id);
        BeanUtil.copyProperties(omsOrderReturnApply,omsOrderReturnApplyResult);
        return omsOrderReturnApplyResult;
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
