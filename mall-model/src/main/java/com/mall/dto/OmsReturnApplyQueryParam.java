package com.mall.dto;


import lombok.Data;

import java.io.Serializable;


/**
 * 订单退货申请查询参数
 */
@Data
public class OmsReturnApplyQueryParam implements Serializable {
    private Long id;//服务单号

    private String receiverKeyword;//收货人姓名/号码

    private Integer status;//申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝

    private String createTime;//申请时间

    private String handleMan;//处理人员

    private String handleTime;//处理时间
}
