package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
//获取所有商品优选
@Data
public class CmsPrefrenceArea implements Serializable {

  private Long id;
  private String name;
  private String subTitle;
  private String pic;
  private Integer sort;
  private Integer showStatus;

}
