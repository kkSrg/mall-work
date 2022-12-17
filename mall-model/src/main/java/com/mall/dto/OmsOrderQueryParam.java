package com.mall.dto;

import lombok.Data;


/**
 * 订单查询参数
 */
@Data
public class OmsOrderQueryParam {
    private String orderSn;
    private String receiverKeyword;
    private Integer status;
    private Integer orderType;
    private Integer sourceType;
    private String createTime;
}
