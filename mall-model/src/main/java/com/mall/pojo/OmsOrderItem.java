package com.mall.pojo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class OmsOrderItem implements Serializable {
    private Long id;

    private Long orderId;//订单id

    private String orderSn;//订单编号

    private Long productId;

    private String productPic;

    private String productName;

    private String productBrand;

    private String productSn;

    private BigDecimal productPrice;//销售价格

    private Integer productQuantity;//购买数量

    private Long productSkuId;//商品sku编号

    private String productSkuCode;//商品sku条码

    private Long productCategoryId;//商品分类id

    private String promotionName;//商品促销名称

    private BigDecimal promotionAmount;//商品促销分解金额

    private BigDecimal couponAmount;//优惠券优惠分解金额

    private BigDecimal integrationAmount;//积分优惠分解金额

    private BigDecimal realAmount;//该商品经过优惠后的分解金额

    private Integer giftIntegration;

    private Integer giftGrowth;

    private String productAttr;//商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]

    private static final long serialVersionUID = 1L;



}