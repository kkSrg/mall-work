package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
//会员等级表
@Data
public class UmsMemberLevel implements Serializable {

  private Long id;
  private String name;
  private Integer growthPoint;
  private Integer defaultStatus;
  private Double freeFreightPoint;
  private Integer commentGrowthPoint;
  private Integer priviledgeFreeFreight;
  private Integer priviledgeSignIn;
  private Integer priviledgeComment;
  private Integer priviledgePromotion;
  private Integer priviledgeMemberPrice;
  private Integer priviledgeBirthday;
  private String note;


}
