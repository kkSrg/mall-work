package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.OmsOrderApi;
import com.mall.api.admin.OrderOperateHistoryApi;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.dto.OmsReceiverInfoParam;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderOperateHistory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OmsOrderService {
    @DubboReference
    private OmsOrderApi omsOrderApi;
    @DubboReference
    private OrderOperateHistoryApi orderOperateHistoryApi;

    /**
     * 查看订单
     * @param queryParam
     * @param pageNum
     * @param pageSize
     * @return
     */
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
    /**
     * 批量发货
     * @param deliveryParamList
     * @return
     */
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
    /**
     * 批量关闭订单
     * @param ids
     * @param note
     * @return
     */
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        int count = omsOrderApi.updateDelivery(record, ids);
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

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        return omsOrderApi.deleteByExampleSelective(record, ids);
    }

    /**
     * 获取订单详情：订单信息、商品信息、操作记录
     * @param id
     * @return
     */
    public OmsOrderDetail detail(Long id) {
        return omsOrderApi.gedetail(id);
    }
    /**
     * 修改收货人信息
     * @param receiverInfoParam
     * @return
     */
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = omsOrderApi.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryApi.insert(history);
        return count;
    }
    /**
     * 备注订单
     * @param id
     * @param note
     * @param status
     * @return
     */
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = omsOrderApi.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryApi.insert(history);
        return count;
    }
}

