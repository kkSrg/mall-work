package com.mall.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 订单修改收货人信息参数
 */
@Data
public class OmsReceiverInfoParam implements Serializable {
    private Long orderId;//订单ID

    private String receiverName;//收货人姓名

    private String receiverPhone;//收货人电话

    private String receiverPostCode;//收货人邮编

    private String receiverDetailAddress;//详细地址

    private String receiverProvince;//省份/直辖市

    private String receiverCity;//城市

    private String receiverRegion;//区

    private Integer status;//订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
}
