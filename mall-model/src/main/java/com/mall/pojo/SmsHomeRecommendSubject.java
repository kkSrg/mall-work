package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsHomeRecommendSubject implements Serializable {
    Integer id;
    Integer subjectId;
    String subjectName;
    int recommendStatus;
    int sort;
}
