package com.mall.api.admin;

import com.mall.pojo.CmsPrefrenceAreaProductRelation;

import java.util.List;

public interface CmsPrefrenceAreaProductRelationApi {
    //根据商品id获取信息
    List<CmsPrefrenceAreaProductRelation> getList(Long id);

    void insert(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);

    void update(Long id, List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);

}
