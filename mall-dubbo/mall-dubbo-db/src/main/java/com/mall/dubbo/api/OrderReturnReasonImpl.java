package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.OrderReturnReasonApi;
import com.mall.dubbo.mapper.OrderReturnReasonMapper;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderReturnReason;
import com.mall.pojo.PmsProduct;
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
    public CommonPage<OmsOrderReturnReason> getPage(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<OmsOrderReturnReason> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(OmsOrderReturnReason::getSort);
        CommonPage<OmsOrderReturnReason> pageResult = new CommonPage<>();
        IPage<OmsOrderReturnReason> page = new Page<>(pageNum,pageSize);
        orderReturnReasonMapper.selectPage(page,wrapper);

        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage((int)page.getPages());
        pageResult.setTotal((int)page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;



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
