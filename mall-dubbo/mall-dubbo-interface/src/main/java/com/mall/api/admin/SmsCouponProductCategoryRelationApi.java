package com.mall.api.admin;

import com.mall.pojo.SmsCouponProductCategoryRelation;

import java.util.List;

/**
 * 优惠券绑定的商品分类
 */
public interface SmsCouponProductCategoryRelationApi {
    /**
     * 保存
     * @param smsCouponProductCategoryRelation
     */
    void save(SmsCouponProductCategoryRelation smsCouponProductCategoryRelation);

    /**
     * 修改
     * @param smsCouponProductCategoryRelation
     */
    void update(SmsCouponProductCategoryRelation smsCouponProductCategoryRelation);

    /**
     *查询
     * @param id
     * @return
     */
    List<SmsCouponProductCategoryRelation> selects(Long id);
}
