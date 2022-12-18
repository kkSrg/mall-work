package com.mall.pojo;



import lombok.Data;

import java.io.Serializable;
@Data
public class OmsCompanyAddress implements Serializable {
    private Long id;

    private String addressName;//地址名称

    private Integer sendStatus;//默认发货地址：0->否；1->是

    private Integer receiveStatus;//是否默认收货地址：0->否；1->是

    private String name;//收发货人姓名

    private String phone;//收货人电话

    private String province;//省/直辖市

    private String city;//市

    private String region;//区

    private String detailAddress;//详细地址

    private static final long serialVersionUID = 1L;


}