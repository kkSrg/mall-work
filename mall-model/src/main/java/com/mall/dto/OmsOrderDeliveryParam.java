package com.mall.dto;

import lombok.Data;


/**
 * 订单发货参数
 */
@Data
public class OmsOrderDeliveryParam {
    private Long orderId;//订单id
    private String deliveryCompany;//物流公司
    private String deliverySn;//物流单号
}
