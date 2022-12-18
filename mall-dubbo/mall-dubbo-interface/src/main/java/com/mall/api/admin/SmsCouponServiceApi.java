package com.mall.api.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.pojo.SmsCoupon;

import java.util.List;

/**
 * 优惠券表
 *
 * @author 吴一飞
 * @date 2022/12/17 15:05
 */

public interface SmsCouponServiceApi {
    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     */
    Page<SmsCoupon> getPage(Integer pageNum, Integer pageSize, String name, Integer type);
}
