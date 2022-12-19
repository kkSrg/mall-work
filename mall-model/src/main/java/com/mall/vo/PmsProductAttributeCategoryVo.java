package com.mall.vo;

import com.mall.pojo.PmsProductAttribute;
import com.mall.pojo.PmsProductAttributeCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsProductAttributeCategoryVo extends PmsProductAttributeCategory implements Serializable {

    private List<PmsProductAttribute> productAttributeList; //商品属性分类其下的所有属性
}
