package com.mall.dto;

import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderItem;
import com.mall.pojo.OmsOrderOperateHistory;
import lombok.Data;
import java.util.List;


/**
 * 订单详情信息
 */
@Data
public class OmsOrderDetail extends OmsOrder {

    private List<OmsOrderItem> orderItemList;//订单商品列表

    private List<OmsOrderOperateHistory> historyList;//订单操作记录列表
}
