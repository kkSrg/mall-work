package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.OmsOrderApi;
import com.mall.api.admin.OrderOperateHistoryApi;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderExample;
import com.mall.pojo.OmsOrderOperateHistory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OmsOrderService {
    @DubboReference
    private OmsOrderApi omsOrderApi;

    private OrderOperateHistoryApi orderOperateHistoryApi;


    public CommonPage<OmsOrder> getPage(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        CommonPage<OmsOrder> result=new CommonPage<>();
        result.setTotalPage(1);
        result.setTotal(10);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        List<OmsOrder> list=omsOrderApi.getList(queryParam);
        result.setList(list);
        return result;

    }

    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = omsOrderApi.delivery(deliveryParamList);
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
    orderOperateHistoryApi.insertList(operateHistoryList);
        return count;

    }

    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
        int count = omsOrderApi.updateByExampleSelective(record, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryApi.insertList(historyList);
        return count;
    }

    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
        return omsOrderApi.updateByExampleSelective(record, example);
    }


    public OmsOrderDetail detail(Long id) {
        return null;
    }
}
