package com.mall.dubbo.api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.SmsCouponServiceApi;
import com.mall.dubbo.mapper.SmsCouponMapper;
import com.mall.pojo.SmsCoupon;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 优惠券表
 *
 * @author 吴一飞
 * @date 2022/12/17 15:05
 */

@DubboService
public class SmsCouponServiceApiImpl implements SmsCouponServiceApi {
    @Autowired
    private SmsCouponMapper smsCouponMapper;

    @Override
    public List<SmsCoupon> getPage(String name, Integer type) {
        LambdaQueryWrapper<SmsCoupon> wrapper = new LambdaQueryWrapper<>();
        List<SmsCoupon> list;
        if (StrUtil.isNotEmpty(name) && ObjectUtil.isNotEmpty(type)) {
            wrapper.like(SmsCoupon::getName, name).like(SmsCoupon::getType, type);
            list = smsCouponMapper.selectList(wrapper);
        } else if (StrUtil.isEmpty(name) && ObjectUtil.isNotEmpty(type)) {
            wrapper.like(SmsCoupon::getType, type);
            list = smsCouponMapper.selectList(wrapper);
        } else if (ObjectUtil.isEmpty(type) && StrUtil.isNotEmpty(name)) {
            wrapper.like(SmsCoupon::getName, name);
            list = smsCouponMapper.selectList(wrapper);
        }else {
            list = smsCouponMapper.selectList(null);
        }

        return list;
    }
}
