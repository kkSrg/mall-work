package com.mall.api.admin;

import com.mall.pojo.PmsProductAttribute;

import java.util.List;

public interface PmsProductAttributeApi {

    //根据产品分类id查找其下的所有属性参数
    List<PmsProductAttribute> selectAllAttributeByCategoryId(long id);
}
