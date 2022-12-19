package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.CmsPrefrenceAreaProductRelationApi;
import com.mall.dubbo.mapper.CmsPrefrenceAreaProductRelationMapper;
import com.mall.pojo.CmsPrefrenceAreaProductRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class CmsPrefrenceAreaProductRelationApiImpl implements CmsPrefrenceAreaProductRelationApi {

    @Autowired
    private CmsPrefrenceAreaProductRelationMapper mapper;

    @Override
    public List<CmsPrefrenceAreaProductRelation> getList(Long id) {
        LambdaQueryWrapper<CmsPrefrenceAreaProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,CmsPrefrenceAreaProductRelation::getProductId,id);
        List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations = mapper.selectList(queryWrapper);
        return cmsPrefrenceAreaProductRelations;
    }

    @Override
    public void insert(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList) {
        for (CmsPrefrenceAreaProductRelation cmsPrefrenceAreaProductRelation : prefrenceAreaProductRelationList) {
            mapper.insert(cmsPrefrenceAreaProductRelation);
        }
    }

    @Override
    public void update(Long id, List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList) {
        LambdaQueryWrapper<CmsPrefrenceAreaProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,CmsPrefrenceAreaProductRelation::getProductId,id);
        mapper.delete(queryWrapper);
        for (CmsPrefrenceAreaProductRelation cmsPrefrenceAreaProductRelation : prefrenceAreaProductRelationList) {
            mapper.insert(cmsPrefrenceAreaProductRelation);
        }
    }
}
