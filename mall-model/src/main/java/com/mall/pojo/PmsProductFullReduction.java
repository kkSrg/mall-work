package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

//产品满减表
@Data
public class PmsProductFullReduction implements Serializable {

  private Long id;
  private Long productId;
  private Double fullPrice; //满金额
  private Double reducePrice; //减金额


}
