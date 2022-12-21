package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;



/**
 * 优惠券和产品的关系表(SmsCouponProductRelation)实体类
 *
 * @author wuyifei
 * @since 2022-12-19 14:37:35
 */
@Data
public class SmsCouponProductRelation  implements Serializable {

    private static final long serialVersionUID = 868124447696529166L;

    private Long id;

    private Long couponId;

    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品编码
     */
    private String productSn;
}
