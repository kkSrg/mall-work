package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

//sku的库存
@Data
public class PmsSkuStock implements Serializable {

  private Long id;
  private Long productId;  //商品id
  private String skuCode;  //sku编码
  private Double price;
  private Integer stock; //库存
  private Integer lowStock; //预警库存
  private String pic; //展示图片
  private Integer sale; //销量
  private Double promotionPrice; //单品促销价格
  private Integer lockStock; //锁定库存
  private String spData; //商品销售属性，json格式



}
