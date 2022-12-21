package com.mall.admin.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.PmsProductAttributeApi;
import com.mall.api.admin.PmsProductCategoryAttributeRelationApi;
import com.mall.pojo.PmsProductAttribute;
import com.mall.pojo.PmsProductCategoryAttributeRelation;
import com.mall.vo.AttributeVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsProductAttributeService {

    @DubboReference
    private PmsProductAttributeApi pmsProductAttributeApi;

    @DubboReference
    private PmsProductCategoryAttributeRelationApi pmsProductCategoryAttributeRelationApi;



    /**
     * 1.根据分类查询属性列表或参数列表
     * @return
     */
    public CommonPage<PmsProductAttribute> list(Integer cid, Integer type, Integer pageNum, Integer pageSize) {
        CommonPage<PmsProductAttribute> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        IPage<PmsProductAttribute> iPage = pmsProductAttributeApi.selectByCidAndType(pageNum, pageSize, Convert.toLong(cid), type);
        result.setTotal(Convert.toInt(iPage.getTotal()));
        result.setTotalPage(Convert.toInt(iPage.getPages()));
        result.setList(iPage.getRecords());
        return result;
    }

    /**
     * 1.根据分类查询属性列表或参数列表
     *
     * @return
     */
    public List<PmsProductAttribute> listOther(Integer cid, Integer type) {
        return pmsProductAttributeApi.selectAttribute(Convert.toLong(cid), type);
    }

    /**
     * 2.根据商品分类的id获取商品属性及属性分类
     *
     * @return
     */
    public List<AttributeVo> attrInfo(Integer productCategoryId) {
        List<Long> attributeIds = pmsProductCategoryAttributeRelationApi.selectAttributeIds(productCategoryId);
        List<AttributeVo> voList = attributeIds.stream().map(attributeId -> {
            AttributeVo vo = new AttributeVo();
            LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(null != attributeId, PmsProductAttribute::getId, attributeId);
            PmsProductAttribute attribute = pmsProductAttributeApi.getMsgByAttributeId(attributeId);
            vo.setAttributeId(attributeId);
            vo.setAttributeCategoryId(attribute.getProductAttributeCategoryId());
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    /**
     * 3.查询单个商品属性
     *
     * @param id
     * @return
     */
    public PmsProductAttribute getMsgById(Integer id) {
        PmsProductAttribute attribute = pmsProductAttributeApi.getMsgByAttributeId(Convert.toLong(id));
        return attribute;
    }

    /**
     * 4.添加商品属性信息
     *
     * @return
     */
    public void create(PmsProductAttribute attribute) {
        pmsProductAttributeApi.create(attribute);
    }

    /**
     * 5.批量删除商品属性
     *
     * @param ids
     * @return
     */
    public void delete(List<Integer> ids) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductAttributeApi.delete(idList);
    }

    /**
     * 6.修改商品属性信息
     *
     * @param id
     * @param attribute
     * @return
     */
    public void update(Integer id, PmsProductAttribute attribute) {
        pmsProductAttributeApi.update(Convert.toLong(id), attribute);
    }


}
