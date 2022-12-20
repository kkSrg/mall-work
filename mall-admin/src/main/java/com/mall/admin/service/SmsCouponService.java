package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.SmsCouponProductCategoryRelationApi;
import com.mall.api.admin.SmsCouponProductRelationServiceApi;
import com.mall.api.admin.SmsCouponServiceApi;
import com.mall.dto.SmsCouponDTO;
import com.mall.pojo.SmsCoupon;
import com.mall.pojo.SmsCouponProductCategoryRelation;
import com.mall.pojo.SmsCouponProductRelation;
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

    @DubboReference
    private SmsCouponProductCategoryRelationApi smsCouponProductCategoryRelationApi;

    @DubboReference
    private SmsCouponProductRelationServiceApi smsCouponProductRelationServiceApi;


    /**
     * 根据优惠券名称和类型分页获取优惠券列表
     *
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

        //根据优惠券名称和类型分页获取优惠券列表
        Page<SmsCoupon> page = smsCouponServiceApi.getPage(pageNum, pageSize, name, type);
        result.setList(page.getRecords());

        //分页计算
        double ceil = Math.ceil(Convert.toDouble(page.getTotal()) / Convert.toDouble(pageSize));
        result.setTotalPage(Convert.toInt(ceil));
        result.setTotal(Convert.toInt(page.getTotal()));
        return result;
    }

    /**
     * 添加优惠券
     *
     * @param couponParam
     */
    public void addSmsCoupon(SmsCouponDTO couponParam) {
        //优惠券和产品分类关系
        List<SmsCouponProductCategoryRelation> productCategoryRelationList = couponParam.getProductCategoryRelationList();
        //遍历
        for (SmsCouponProductCategoryRelation smsCouponProductCategoryRelation : productCategoryRelationList) {
            //远程调用service 保存数据
            smsCouponProductCategoryRelationApi.save(smsCouponProductCategoryRelation);
        }

        //优惠券和产品的关系
        List<SmsCouponProductRelation> productRelationList = couponParam.getProductRelationList();
        for (SmsCouponProductRelation smsCouponProductRelation : productRelationList) {
            //远程调用service 保存数据
            smsCouponProductRelationServiceApi.save(smsCouponProductRelation);
        }
        //创建实体类
        SmsCoupon smsCoupon = new SmsCoupon();
        //接收数据
        BeanUtil.copyProperties(couponParam,smsCoupon);
        //远程调用service 保存数据
        smsCouponServiceApi.save(smsCoupon);


    }

    /**
     * 删除优惠券
     * @param id
     */
    public void deleteById(Long id) {
        smsCouponServiceApi.deleteById(id);
    }

    /**
     * 修改优惠券
     * @param couponParam
     */
    public void updateSmsCoupon(SmsCouponDTO couponParam) {
        //优惠券和产品分类关系
        List<SmsCouponProductCategoryRelation> productCategoryRelationList = couponParam.getProductCategoryRelationList();
        //遍历
        for (SmsCouponProductCategoryRelation smsCouponProductCategoryRelation : productCategoryRelationList) {
            //远程调用service 保存数据
            smsCouponProductCategoryRelationApi.update(smsCouponProductCategoryRelation);
        }

        //优惠券和产品的关系
        List<SmsCouponProductRelation> productRelationList = couponParam.getProductRelationList();
        for (SmsCouponProductRelation smsCouponProductRelation : productRelationList) {
            //远程调用service 保存数据
            smsCouponProductRelationServiceApi.update(smsCouponProductRelation);
        }
        //创建实体类
        SmsCoupon smsCoupon = new SmsCoupon();
        //接收数据
        BeanUtil.copyProperties(couponParam,smsCoupon);
        //远程调用service 保存数据
        smsCouponServiceApi.update(smsCoupon);
    }

    /**
     * 查询优惠券
     * @param id
     */
    public SmsCouponDTO smsCouponById(Long id) {
        //查询优惠券
        SmsCoupon smsCoupon = smsCouponServiceApi.selectById(id);
        //查询优惠券和产品的关系表
        List<SmsCouponProductRelation> list = smsCouponProductRelationServiceApi.selects(smsCoupon.getId());
        //查询优惠券和产品分类关系表
        List<SmsCouponProductCategoryRelation> list1 = smsCouponProductCategoryRelationApi.selects(smsCoupon.getId());
        //存到dto中返回
        SmsCouponDTO smsCouponDTO = new SmsCouponDTO();
        //封装
        BeanUtil.copyProperties(smsCoupon,smsCouponDTO);
        if (CollUtil.isNotEmpty(list)){
            smsCouponDTO.setProductRelationList(list);
        }
        if (CollUtil.isNotEmpty(list1)){
            smsCouponDTO.setProductCategoryRelationList(list1);
        }

        //返回
        return smsCouponDTO;

    }
}
