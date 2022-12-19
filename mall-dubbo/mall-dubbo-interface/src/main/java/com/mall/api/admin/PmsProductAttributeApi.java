package com.mall.api.admin;

import com.mall.pojo.PmsProductAttribute;

import java.util.List;

public interface PmsProductAttributeApi {


    //根据分类查询属性列表或参数列表
    List<PmsProductAttribute> selectByCidAndType(List<Long> attributeId, Integer type);

    List<PmsProductAttribute> getMsgByAttributeIds(List<Long> attributeIds);

    PmsProductAttribute getMsgByAttributeId(Long attributeId);

    //添加商品属性信息
    void create(PmsProductAttribute attribute);

    //批量删除商品属性
    void delete(List<Long> idList);

    //修改商品属性信息
    void update(Long toLong, PmsProductAttribute attribute);
}
