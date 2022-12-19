package com.mall.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class OmsOrderReturnReason implements Serializable {

    private Long id;

    private String name;//退货类型

    private Integer sort;

    private Integer status;//状态：0->不启用；1->启用

    private Date createTime;//添加时间

    private static final long serialVersionUID = 1L;

}