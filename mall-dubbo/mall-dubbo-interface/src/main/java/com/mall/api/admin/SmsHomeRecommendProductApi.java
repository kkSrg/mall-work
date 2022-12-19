package com.mall.api.admin;

import com.mall.pojo.SmsHomeRecommendProduct;

import java.util.List;

public interface SmsHomeRecommendProductApi {

    /**
     * 分页查询推荐
     */
    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    Integer finTotal(String productName, Integer recommendStatus);

    int deleteIds(List<Long> ids);

    int  updateCommend(List<Long> ids, Integer recommendStatus);

    void updateSort(Integer id, Integer sort);

    void create(List<SmsHomeRecommendProduct> smsHomeRecommendProductList);
}
