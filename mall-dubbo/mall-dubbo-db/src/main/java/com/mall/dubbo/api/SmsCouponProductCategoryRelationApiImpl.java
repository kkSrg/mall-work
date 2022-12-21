package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.SmsCouponProductCategoryRelationApi;
import com.mall.dubbo.mapper.SmsCouponProductCategoryRelationMapper;
import com.mall.pojo.SmsCouponProductCategoryRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 优惠券绑定的商品分类
 */
@DubboService
public class SmsCouponProductCategoryRelationApiImpl implements SmsCouponProductCategoryRelationApi {

    @Autowired
    private SmsCouponProductCategoryRelationMapper smsCouponProductCategoryRelationMapper;
    /**
     * 保存
     */
    @Override
    public void save(SmsCouponProductCategoryRelation smsCouponProductCategoryRelation) {

        smsCouponProductCategoryRelationMapper.insert(smsCouponProductCategoryRelation);

    }

    @Override
    public void update(SmsCouponProductCategoryRelation smsCouponProductCategoryRelation) {
        smsCouponProductCategoryRelationMapper.updateById(smsCouponProductCategoryRelation);
    }

    @Override
    public List<SmsCouponProductCategoryRelation> selects(Long id) {

        LambdaQueryWrapper<SmsCouponProductCategoryRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SmsCouponProductCategoryRelation::getCouponId,id);
       return smsCouponProductCategoryRelationMapper.selectList(wrapper);

    }
}
