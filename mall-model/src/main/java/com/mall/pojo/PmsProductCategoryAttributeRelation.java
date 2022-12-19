package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
//产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
@Data
public class PmsProductCategoryAttributeRelation implements Serializable {

  private Long id;
  private Long productCategoryId;  //产品分类id
  private Long productAttributeId;  //产品属性id



}
