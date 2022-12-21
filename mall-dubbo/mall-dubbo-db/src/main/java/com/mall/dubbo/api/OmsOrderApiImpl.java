package com.mall.dubbo.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.OmsOrderApi;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.dubbo.mapper.OmsOrderMapper;
import com.mall.dubbo.mapper.OrderOperateHistoryMapper;
import com.mall.pojo.OmsOrder;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class OmsOrderApiImpl implements OmsOrderApi {
    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;



    @Override
    public void delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //创建实体类接收数据
        OmsOrder omsOrder = new OmsOrder();
        for (OmsOrderDeliveryParam orderDeliveryParam : deliveryParamList) {
            //copy数据
            BeanUtil.copyProperties(orderDeliveryParam,omsOrder);
            LambdaQueryWrapper<OmsOrder> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(OmsOrder::getId,orderDeliveryParam.getOrderId());
            omsOrderMapper.update(omsOrder, wrapper);

            /*OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderDeliveryParam.getOrderId());
            history.setOperateMan("后台管理员");
            history.setCreateTime(DateTime.now());
            history.setOrderStatus(2);
            history.setNote("已发货");
            orderOperateHistoryMapper.insert(history);*/
        }
    }


    @Override
    public List<OmsOrder> getList(OmsOrderQueryParam queryParam, Integer pageNum, Integer pageSize, CommonPage<OmsOrder> result){
  /*     IPage<OmsOrder> page = new Page<>(pageNum, pageSize);
        //构建条件
        LambdaQueryWrapper<OmsOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryParam.getOrderSn() != null, OmsOrder::getOrderSn, queryParam.getOrderSn());
        wrapper.like(queryParam.getReceiverKeyword() != null, OmsOrder::getReceiverName, queryParam.getReceiverKeyword()).or()
                .like(queryParam.getReceiverKeyword() != null, OmsOrder::getReceiverPhone, queryParam.getReceiverKeyword());
        wrapper.like(queryParam.getStatus()!=null,OmsOrder::getStatus, queryParam.getStatus());
        wrapper.like(queryParam.getOrderType()!=null, OmsOrder::getOrderType, queryParam.getOrderType());
        wrapper.like(queryParam.getSourceType()!=null, OmsOrder::getSourceType, queryParam.getSourceType());
        wrapper.like(queryParam.getCreateTime()!=null, OmsOrder::getCreateTime, queryParam.getCreateTime());
        omsOrderMapper.selectPage(page, wrapper);
        result.setTotalPage((int) page.getPages());
        result.setTotal((int) page.getTotal());
        return page.getRecords();*/
        return null;
    }
    @Override
    public CommonPage<OmsOrder> getPage(OmsOrderQueryParam queryParam, Integer pageNum, Integer pageSize) {
        IPage<OmsOrder> page = new Page<>(pageNum, pageSize);
        //构建条件
        LambdaQueryWrapper<OmsOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryParam.getOrderSn() != null, OmsOrder::getOrderSn, queryParam.getOrderSn());
        wrapper.like(queryParam.getReceiverKeyword() != null, OmsOrder::getReceiverName, queryParam.getReceiverKeyword()).or()
                .like(queryParam.getReceiverKeyword() != null, OmsOrder::getReceiverPhone, queryParam.getReceiverKeyword());
        wrapper.like(queryParam.getStatus()!=null,OmsOrder::getStatus, queryParam.getStatus());
        wrapper.like(queryParam.getOrderType()!=null, OmsOrder::getOrderType, queryParam.getOrderType());
        wrapper.like(queryParam.getSourceType()!=null, OmsOrder::getSourceType, queryParam.getSourceType());
        wrapper.like(queryParam.getCreateTime()!=null, OmsOrder::getCreateTime, queryParam.getCreateTime());
        omsOrderMapper.selectPage(page, wrapper);

        CommonPage<OmsOrder> pageResult = new CommonPage<>();
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage(Convert.toInt(page.getPages()));
        pageResult.setTotal(Convert.toInt(page.getTotal()));
        pageResult.setList(page.getRecords());
        return pageResult;
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
        OmsOrderDetail detail = new OmsOrderDetail();
        OmsOrder omsOrder = omsOrderMapper.selectById(id);
        BeanUtil.copyProperties(omsOrder,detail);
        return detail;
    }

    @Override
    public int updateByPrimaryKeySelective(OmsOrder order) {

        return omsOrderMapper.updateById(order);
    }




}
