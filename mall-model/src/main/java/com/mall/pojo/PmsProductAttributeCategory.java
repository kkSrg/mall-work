package com.mall.pojo;

import lombok.Data;

/**
 * 商品类型分类表(产品属性分类表)
 */
@Data
public class PmsProductAttributeCategory {

  private long id;  //编号
  private String name;  //类型名称
  private Integer attributeCount;  //属性数量
  private Integer paramCount;  //参数数量



}
