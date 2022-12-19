package com.mall.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeVo implements Serializable {

    private Long attributeCategoryId; //商品属性分类id
    private Long attributeId; //商品属性id
}
