package com.mall.admin.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.SmsHomeRecommendProductApi;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsHomeRecommendProductService {

    @DubboReference
    private SmsHomeRecommendProductApi smsHomeRecommendProductApi;

    public CommonPage<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum){
        //查询数据到分页的records中返回到service
        List<SmsHomeRecommendProduct> pageInfoList = smsHomeRecommendProductApi.list(productName, recommendStatus, pageSize, pageNum);
        Integer total = smsHomeRecommendProductApi.finTotal(productName,recommendStatus);
        CommonPage<SmsHomeRecommendProduct> result = new CommonPage<>();
        //封装5个中的两个
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setList(pageInfoList);
        result.setTotal(total);
        Integer totalPage = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        result.setTotalPage(totalPage);
        //这一步是遍历records来获得表里的总数居
//        List<SmsHomeRecommendProduct> voList=pageInfoList.stream().map(recommendUser -> {
//        //遍历一个数据封装在实体类
//            SmsHomeRecommendProduct smsHomeRecommendProduct = new SmsHomeRecommendProduct();
//            BeanUtils.copyProperties(recommendUser, smsHomeRecommendProduct);
//            //在根据这个实体类的id查找sum
//            result.setTotal(smsHomeRecommendProductApi.count(smsHomeRecommendProduct.getId()));
//            return smsHomeRecommendProduct;
//        }).collect(Collectors.toList());
        return result;

    }
    /**
    *删除
    * */
    public int delete(List<Long> ids) {
        if (ids!=null){
            int count = smsHomeRecommendProductApi.deleteIds(ids);
            return count;
        }
        return -1;
    }

/**
 * 修改推荐
 *
 */
    public int updateCommend(List<Long> ids, Integer recommendStatus) {
        int count = smsHomeRecommendProductApi.updateCommend(ids,recommendStatus);
        return count;
    }

    //修改推荐排序
    public void updateSort(Integer id, Integer sort) {
        smsHomeRecommendProductApi.updateSort(id,sort);
    }

    //首页添加新品
    public void create(List<SmsHomeRecommendProduct> smsHomeRecommendProductList) {
        smsHomeRecommendProductApi.create(smsHomeRecommendProductList);
    }
}
