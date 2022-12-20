package com.mall.api.admin;

import com.mall.pojo.CmsSubject;
import com.mall.pojo.SmsHomeRecommendProduct;

import java.util.List;

public interface CmsSubjectApi {
    List<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum);

    Integer finTotal(String keyword);

    List<CmsSubject> listAll();
}
