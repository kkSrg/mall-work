package com.mall.dubbo.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.PmsProductAttributeApi;
import com.mall.dubbo.mapper.PmsProductAttributeMapper;
import com.mall.pojo.PmsProductAttribute;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class PmsProductAttributeApiImpl implements PmsProductAttributeApi {

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;



    //根据分类查询属性列表或参数列表
    @Override
    public IPage<PmsProductAttribute> selectByCidAndType(Integer pageNum, Integer pageSize,Long cid, Integer type) {
        IPage<PmsProductAttribute> pg = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=cid,PmsProductAttribute::getProductAttributeCategoryId,cid);
        queryWrapper.eq(null!=type,PmsProductAttribute::getType,type);
        pmsProductAttributeMapper.selectPage(pg,queryWrapper);
        return pg;
    }

    @Override
    public List<PmsProductAttribute> getMsgByAttributeIds(List<Long> attributeIds) {
        List<PmsProductAttribute> list = attributeIds.stream().map(attributeId->{
            return pmsProductAttributeMapper.selectById(attributeId);
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public PmsProductAttribute getMsgByAttributeId(Long attributeId) {
        PmsProductAttribute attribute = pmsProductAttributeMapper.selectById(attributeId);
        return attribute;
    }

    //添加商品属性信息
    @Override
    public void create(PmsProductAttribute attribute) {
        pmsProductAttributeMapper.insert(attribute);
    }

    //批量删除商品属性
    @Override
    public void delete(List<Long> idList) {
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProductAttribute::getId,idList);
        pmsProductAttributeMapper.delete(queryWrapper);
    }

    //修改商品属性信息
    @Override
    public void update(Long id, PmsProductAttribute attribute) {
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductAttribute::getId,id);
        pmsProductAttributeMapper.update(attribute,queryWrapper);
    }

    @Override
    public List<PmsProductAttribute> selectAttribute(Long cid, Integer type) {
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=cid,PmsProductAttribute::getProductAttributeCategoryId,cid);
        queryWrapper.eq(null!=type,PmsProductAttribute::getType,type);
        return pmsProductAttributeMapper.selectList(queryWrapper);
    }
}
