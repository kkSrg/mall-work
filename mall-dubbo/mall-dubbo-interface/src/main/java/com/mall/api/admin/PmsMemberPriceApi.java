package com.mall.api.admin;

import com.mall.pojo.PmsMemberPrice;

import java.util.List;

public interface PmsMemberPriceApi {
    //根据商品id获取信息
    List<PmsMemberPrice> getList(Long id);

    void insert(List<PmsMemberPrice> memberPriceList);

    void update(Long id, List<PmsMemberPrice> memberPriceList);
}
