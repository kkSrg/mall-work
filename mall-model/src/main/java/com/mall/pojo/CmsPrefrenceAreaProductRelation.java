package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
//优选专区和产品关系表
@Data
public class CmsPrefrenceAreaProductRelation implements Serializable {

  private Long id;
  private Long prefrenceAreaId;
  private Long productId;


}
