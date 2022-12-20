package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.PmsProductAttribute;

import java.util.List;

public interface PmsProductAttributeApi {


    //根据分类查询属性列表或参数列表
    IPage<PmsProductAttribute> selectByCidAndType(Integer pageNum, Integer pageSize,List<Long> attributeIds, Integer type);

    List<PmsProductAttribute> getMsgByAttributeIds(List<Long> attributeIds);

    PmsProductAttribute getMsgByAttributeId(Long attributeId);

    //添加商品属性信息
    void create(PmsProductAttribute attribute);

    //批量删除商品属性
    void delete(List<Long> idList);

    //修改商品属性信息
    void update(Long toLong, PmsProductAttribute attribute);

    //根据分类查询属性列表或参数列表
    List<PmsProductAttribute> selectAttribute(List<Long> attributeIds, Integer type);
}
