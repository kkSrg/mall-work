package com.mall.api.admin;

import com.mall.pojo.SmsCouponProductRelation;

import java.util.List;

/**
 * 优惠券和产品的关系表
 */
public interface SmsCouponProductRelationServiceApi {
    /**
     * 保存
     * @param smsCouponProductRelation
     */
    void save(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 修改
     * @param smsCouponProductRelation
     */
    void update(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 查询
     * @param id
     * @return
     */
    List<SmsCouponProductRelation> selects(Long id);
}
