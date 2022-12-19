package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductCategoryAttributeRelationApi;
import com.mall.dubbo.mapper.PmsProductCategoryAttributeRelationMapper;
import com.mall.pojo.PmsProductCategoryAttributeRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class PmsProductCategoryAttributeRelationApiImpl implements PmsProductCategoryAttributeRelationApi {

    @Autowired
    private PmsProductCategoryAttributeRelationMapper mapper;

    @Override
    public List<Long> selectAttributeId(Integer cid) {
        LambdaQueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=cid,PmsProductCategoryAttributeRelation::getProductCategoryId,cid);
        List<PmsProductCategoryAttributeRelation> relationList = mapper.selectList(queryWrapper);
        List<Long> attributeId = relationList.stream().map(relation->{
            return relation.getProductAttributeId();
        }).collect(Collectors.toList());
        return attributeId;
    }
}
