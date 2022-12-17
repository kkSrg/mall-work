package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.SmsCouponServiceApi;
import com.mall.pojo.SmsCoupon;
import com.mall.pojo.SmsFlashPromotion;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券表
 *
 * @author 吴一飞
 * @date 2022/12/17 15:05
 */

@Service
public class SmsCouponService {

    @DubboReference
    private SmsCouponServiceApi smsCouponServiceApi;


    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param type
     * @return
     */
    public CommonPage<SmsCoupon> smsCouponPage(Integer pageNum, Integer pageSize, String name, Integer type) {

        CommonPage<SmsCoupon> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotalPage(1);
        result.setTotal(10);

        //根据优惠券名称和类型分页获取优惠券列表
        List<SmsCoupon> list = smsCouponServiceApi.getPage(name,type);
        result.setList(list);
        return result;
    }
}
