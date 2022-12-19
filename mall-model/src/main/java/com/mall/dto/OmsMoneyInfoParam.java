package com.mall.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 修改订单费用信息参数
 */
@Data
public class OmsMoneyInfoParam {
    private Long orderId;//订单ID

    private BigDecimal freightAmount;//运费金额

    private BigDecimal discountAmount;//管理员后台调整订单所使用的折扣金额

    private Integer status;//订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
}
