package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsHomeRecommendProduct implements Serializable {
        Integer id;
        Integer productId;
        String productName;
        int recommendStatus;
        int sort;
}
