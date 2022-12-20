package com.mall.dubbo.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public IPage selectAttributeId(Integer pageNum, Integer pageSize,Integer cid) {
        IPage<PmsProductCategoryAttributeRelation> pg = new Page(pageNum,pageSize);
        LambdaQueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=cid,PmsProductCategoryAttributeRelation::getProductCategoryId,cid);
        mapper.selectPage(pg,queryWrapper);

        return pg;
    }

    @Override
    public List<Long> selectAttributeIds(Integer productCategoryId) {
        LambdaQueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=productCategoryId,PmsProductCategoryAttributeRelation::getProductCategoryId, Convert.toLong(productCategoryId));
        List<PmsProductCategoryAttributeRelation> relationList = mapper.selectList(queryWrapper);

        return CollUtil.getFieldValues(relationList,"productAttributeId",Long.class);
    }
}
