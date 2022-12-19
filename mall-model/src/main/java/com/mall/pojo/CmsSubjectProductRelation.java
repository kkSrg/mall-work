package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

//专题商品关系表
@Data
public class CmsSubjectProductRelation implements Serializable {

  private Long id;
  private Long subjectId;
  private Long productId;


}
