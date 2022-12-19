package com.mall.dto;


import lombok.Data;


import java.math.BigDecimal;

/**
 * 确认收货请求参数
 */
@Data
public class OmsUpdateStatusParam {

    private Long id;//服务单号

    private Long companyAddressId;//收货地址关联id

    private BigDecimal returnAmount;//确认退款金额

    private String handleNote;//处理备注

    private String handleMan;//处理人

    private String receiveNote;//收货备注

    private String receiveMan;//收货人

    private Integer status;//申请状态：1->退货中；2->已完成；3->已拒绝
}
