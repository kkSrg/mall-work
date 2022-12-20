package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.SmsCouponProductRelationServiceApi;
import com.mall.dubbo.mapper.SmsCouponProductRelationMapper;
import com.mall.pojo.SmsCouponProductRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 优惠券和产品的关系表
 */
@DubboService
public class SmsCouponProductRelationServiceApiImpl implements SmsCouponProductRelationServiceApi {

    @Autowired
    private SmsCouponProductRelationMapper smsCouponProductRelationMapper;
    /**
     * 保存
     * @param smsCouponProductRelation
     */
    @Override
    public void save(SmsCouponProductRelation smsCouponProductRelation) {

        smsCouponProductRelationMapper.insert(smsCouponProductRelation);
    }

    @Override
    public void update(SmsCouponProductRelation smsCouponProductRelation) {
        smsCouponProductRelationMapper.updateById(smsCouponProductRelation);
    }

    @Override
    public List<SmsCouponProductRelation> selects(Long id) {

        LambdaQueryWrapper<SmsCouponProductRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SmsCouponProductRelation::getCouponId,id);
        return smsCouponProductRelationMapper.selectList(wrapper);
    }
}
