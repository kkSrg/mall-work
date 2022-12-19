package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌列表
 */
@Data
public class PmsBrand implements Serializable {

  private Long id;  //品牌编号
  private String name;  //品牌名
  private String firstLetter;  //首字母
  private Integer sort;  //排序
  private Integer factoryStatus;  //是否为品牌制造商：0->不是；1->是
  private Integer showStatus;  //是否显示
  private Integer productCount;  //产品数量
  private Integer productCommentCount;  //产品评论数量
  private String logo;  //品牌logo
  private String bigPic;  //专区大图
  private String brandStory;  //品牌故事

}
