package com.mall.dubbo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.pojo.SmsCoupon;
import org.apache.ibatis.annotations.Mapper;
/**
 * 优惠券表
 *
 * @author 吴一飞
 * @date 2022/12/17 15:05
 */

@Mapper
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {
}
