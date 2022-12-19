package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

//商品会员价格表
@Data
public class PmsMemberPrice implements Serializable {

  private Long id;
  private Long productId;
  private Long memberLevelId;
  private Double memberPrice; //会员价格
  private String memberLevelName;


}
