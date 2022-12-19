package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

//产品阶梯价格表
@Data
public class PmsProductLadder implements Serializable {

  private Long id;
  private Long productId; //商品id
  private Integer count; //满足的商品数量
  private Double discount; //折扣
  private Double price; //折后价格


}
