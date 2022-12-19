package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性参数表
 */
@Data
public class PmsProductAttribute implements Serializable {

  private Long id;  //商品属性id编号
  private Long productAttributeCategoryId;  //产品分类id(商品类型编号)
  private String name;  //属性名称
  private Integer selectType;  //属性选择类型：0->唯一；1->单选；2->多选
  private Integer inputType;  //属性录入方式：0->手工录入；1->从列表中选取
  private String inputList;  //可选值列表，以逗号隔开
  private Integer sort;  //排序字段：最高的可以单独上传图片
  private Integer filterType;  //分类筛选样式：1->普通；1->颜色
  private Integer searchType;  //检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
  private Integer relatedStatus;  //相同属性产品是否关联；0->不关联；1->关联
  private Integer handAddStatus;  //是否支持手动新增；0->不支持；1->支持
  private Integer type;  //属性的类型；0->规格；1->参数

}
