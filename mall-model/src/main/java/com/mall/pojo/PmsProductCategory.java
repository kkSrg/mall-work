package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类列表
 */
@Data
public class PmsProductCategory implements Serializable {

  private Long id;  //分类id(编号)
  private long parentId;  //上级分类的id
  private String name;  //分类名称
  private Integer level;  //级别
  private Integer productCount;  //商品数量
  private String productUnit;  //数量单位
  private Integer navStatus;  //是否显示在导航栏：0->不显示；1->显示
  private Integer showStatus;  //显示状态：0->不显示；1->显示
  private Integer sort;  //排序
  private String icon;  //图标
  private String keywords;  //关键字(数据库中与描述的数据一致)
  private String description;  //描述

}
