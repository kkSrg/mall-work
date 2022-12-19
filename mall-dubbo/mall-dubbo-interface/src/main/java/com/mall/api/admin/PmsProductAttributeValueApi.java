package com.mall.api.admin;

import com.mall.pojo.PmsProductAttributeValue;

import java.util.List;

public interface PmsProductAttributeValueApi {
    //根据商品id获取信息
    List<PmsProductAttributeValue> getList(Long id);

    void insert(List<PmsProductAttributeValue> productAttributeValueList);

    void update(Long id, List<PmsProductAttributeValue> productAttributeValueList);
}
