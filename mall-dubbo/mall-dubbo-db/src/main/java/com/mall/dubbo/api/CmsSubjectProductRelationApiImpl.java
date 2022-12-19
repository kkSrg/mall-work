package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.CmsSubjectProductRelationApi;
import com.mall.dubbo.mapper.CmsSubjectProductRelationMapper;
import com.mall.pojo.CmsSubjectProductRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class CmsSubjectProductRelationApiImpl implements CmsSubjectProductRelationApi {

    @Autowired
    private CmsSubjectProductRelationMapper cmsSubjectProductRelationMapper;

    @Override
    public List<CmsSubjectProductRelation> getList(Long id) {
        LambdaQueryWrapper<CmsSubjectProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,CmsSubjectProductRelation::getProductId,id);
        return cmsSubjectProductRelationMapper.selectList(queryWrapper);
    }

    @Override
    public void insert(List<CmsSubjectProductRelation> subjectProductRelationList) {
        for (CmsSubjectProductRelation cmsSubjectProductRelation : subjectProductRelationList) {
            cmsSubjectProductRelationMapper.insert(cmsSubjectProductRelation);
        }
    }

    @Override
    public void update(Long id, List<CmsSubjectProductRelation> subjectProductRelationList) {
        LambdaQueryWrapper<CmsSubjectProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,CmsSubjectProductRelation::getProductId,id);
        cmsSubjectProductRelationMapper.delete(queryWrapper);
        for (CmsSubjectProductRelation cmsSubjectProductRelation : subjectProductRelationList) {
            cmsSubjectProductRelationMapper.insert(cmsSubjectProductRelation);
        }
    }
}
