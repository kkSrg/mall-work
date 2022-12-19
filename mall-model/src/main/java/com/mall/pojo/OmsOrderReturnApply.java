package com.mall.pojo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OmsOrderReturnApply implements Serializable {

    private Long id;

    private Long orderId;//订单id

    private Long companyAddressId;//收货地址表id

    private Long productId;//退货商品id

    private String orderSn;//订单编号

    private Date createTime;//申请时间

    private String memberUsername;//会员用户名

    private BigDecimal returnAmount;//退款金额

    private String returnName;//退货人姓名

    private String returnPhone;//退货人电话

    private Integer status;//申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝

    private Date handleTime;//处理时间

    private String productPic;//商品图片

    private String productName;//商品名称

    private String productBrand;//商品品牌

    private String productAttr;//商品销售属性：颜色：红色；尺码：xl;

    private Integer productCount;//退货数量

    private BigDecimal productPrice;//商品单价

    private BigDecimal productRealPrice;//商品实际支付单价

    private String reason;//原因

    private String description;//描述

    private String proofPics;//凭证图片，以逗号隔开

    private String handleNote;//处理备注

    private String handleMan;//处理人员

    private String receiveMan;//收货人

    private Date receiveTime;//收货时间

    private String receiveNote;//收货备注

    private static final long serialVersionUID = 1L;


}