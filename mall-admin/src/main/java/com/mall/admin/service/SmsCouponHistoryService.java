package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.api.admin.SmsCouponHistoryServiceApi;
import com.mall.pojo.SmsCouponHistory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 优惠券使用、领取历史表
 */
@Service
public class SmsCouponHistoryService {

    @DubboReference
    private SmsCouponHistoryServiceApi smsCouponHistoryServiceApi;


    /**
     * 根据优惠券id，使用状态，订单编号分页获取领取记录
     */
    public CommonPage<SmsCouponHistory> gerPage(Integer pageNum, Integer pageSize, Integer couponId, String orderSn, Integer useStatus) {

        //创建分页对象
        IPage<SmsCouponHistory> page = new Page<>(pageNum,pageSize);
        IPage<SmsCouponHistory> iPage =  smsCouponHistoryServiceApi.getPage(page,couponId,orderSn,useStatus);

        CommonPage<SmsCouponHistory> commonPage = new CommonPage<>();
        commonPage.setPageNum(pageNum);
        commonPage.setPageSize(pageSize);
        commonPage.setTotal(Math.toIntExact(iPage.getTotal()));
        commonPage.setTotalPage(Math.toIntExact(iPage.getPages()));
        commonPage.setList(iPage.getRecords());
        return commonPage;
    }
}
