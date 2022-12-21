package com.mall.dubbo.api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 分页
     */
    @Override
    public Page<SmsCoupon> getPage(Integer pageNum, Integer pageSize,String name, Integer type) {
        Page<SmsCoupon> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<SmsCoupon> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SmsCoupon::getId);
        Page<SmsCoupon> smsCouponPage;
        if (StrUtil.isNotEmpty(name) && ObjectUtil.isNotEmpty(type)) {
            wrapper.like(SmsCoupon::getName, name).like(SmsCoupon::getType, type);
            smsCouponPage = smsCouponMapper.selectPage(page,wrapper);
        } else if (StrUtil.isEmpty(name) && ObjectUtil.isNotEmpty(type)) {
            wrapper.like(SmsCoupon::getType, type);
            smsCouponPage = smsCouponMapper.selectPage(page,wrapper);
        } else if (ObjectUtil.isEmpty(type) && StrUtil.isNotEmpty(name)) {
            wrapper.like(SmsCoupon::getName, name);
            smsCouponPage = smsCouponMapper.selectPage(page,wrapper);
        }else {
            smsCouponPage = smsCouponMapper.selectPage(page,null);
        }
        return smsCouponPage;
    }

    /**
     * 保存
     */
    @Override
    public void save(SmsCoupon smsCoupon) {
        smsCouponMapper.insert(smsCoupon);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Long id) {
        smsCouponMapper.deleteById(id);
    }

    /**
     * 修改
     */
    @Override
    public void update(SmsCoupon smsCoupon) {
        smsCouponMapper.updateById(smsCoupon);
    }

    @Override
    public SmsCoupon selectById(Long id) {
        return smsCouponMapper.selectById(id);
    }
}
