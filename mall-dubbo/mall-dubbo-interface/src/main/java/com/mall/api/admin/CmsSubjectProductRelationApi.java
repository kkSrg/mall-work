package com.mall.api.admin;

import com.mall.pojo.CmsSubjectProductRelation;

import java.util.List;

public interface CmsSubjectProductRelationApi {
    //根据商品id获取信息
    List<CmsSubjectProductRelation> getList(Long id);

    void insert(List<CmsSubjectProductRelation> subjectProductRelationList);

    void update(Long id, List<CmsSubjectProductRelation> subjectProductRelationList);
}
