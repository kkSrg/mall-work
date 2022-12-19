package com.mall.pojo;


import lombok.Data;

import java.io.Serializable;
//存储产品参数信息的表(商品参数及自定义规格属性)
@Data
public class PmsProductAttributeValue implements Serializable {

  private Long id;
  private Long productId;
  private Long productAttributeId;
  private String value; //手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开


}
