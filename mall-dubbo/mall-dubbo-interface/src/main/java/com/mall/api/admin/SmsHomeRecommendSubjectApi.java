package com.mall.api.admin;

import com.mall.pojo.SmsHomeRecommendProduct;
import com.mall.pojo.SmsHomeRecommendSubject;

import java.util.List;

public interface SmsHomeRecommendSubjectApi {
    List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    Integer finTotal(String subjectName, Integer recommendStatus);

    int deleteIds(List<Long> ids);

    int updateCommend(List<Long> ids, Integer recommendStatus);

    void updateSort(Integer id, Integer sort);

    void create(List<SmsHomeRecommendSubject> smsHomeRecommendSubject);
}
