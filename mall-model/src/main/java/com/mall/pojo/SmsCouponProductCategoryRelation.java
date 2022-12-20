package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券和产品分类关系表(SmsCouponProductCategoryRelation)表实体类
 *
 * @author wuyifei
 * @since 2022-12-19 14:29:19
 */
@Data
public class SmsCouponProductCategoryRelation implements Serializable {

    private Long id;

    private Long couponId;

    private Long productCategoryId;
    //产品分类名称
    private String productCategoryName;
    //父分类名称
    private String parentCategoryName;

}
