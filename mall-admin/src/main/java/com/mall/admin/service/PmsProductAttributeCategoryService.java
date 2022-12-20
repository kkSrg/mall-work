package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.PmsProductAttributeApi;
import com.mall.api.admin.PmsProductAttributeCategoryApi;
import com.mall.api.admin.PmsProductCategoryAttributeRelationApi;
import com.mall.vo.PmsProductAttributeCategoryVo;
import com.mall.exception.ConsumerException;
import com.mall.pojo.PmsProductAttribute;
import com.mall.pojo.PmsProductAttributeCategory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsProductAttributeCategoryService {

    @DubboReference
    private PmsProductAttributeCategoryApi pmsProductAttributeCategoryApi;

    @DubboReference
    private PmsProductAttributeApi pmsProductAttributeApi;

    @DubboReference
    private PmsProductCategoryAttributeRelationApi pmsProductCategoryAttributeRelationApi;


    /**
     * 1.分页获取所有商品属性分类
     * @return
     */
    public CommonPage<PmsProductAttributeCategory> list(Integer pageNum, Integer pageSize) {
        IPage pg= pmsProductAttributeCategoryApi.findAllProductAttributeCategory(pageNum,pageSize);
        CommonPage<PmsProductAttributeCategory> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotalPage(Convert.toInt(pg.getPages()));
        result.setTotal(Convert.toInt(pg.getTotal()));
        result.setList(pg.getRecords());
        return result;
    }

    /**
     * 2.获取所有商品属性分类及其下属性
     * @return
     */
    public List<PmsProductAttributeCategoryVo> listWithAttr() {
       /* List<PmsProductAttributeCategory> categoryList = pmsProductAttributeCategoryApi.findAllProductAttributeCategory();
        List<PmsProductAttributeCategoryVo> result = categoryList.stream().map(category -> {
            PmsProductAttributeCategoryVo dto = new PmsProductAttributeCategoryVo();
            BeanUtil.copyProperties(category,dto);
            List<Long> attributeIds = pmsProductCategoryAttributeRelationApi.selectAttributeIds(Convert.toInt(category.getId()));
            List<PmsProductAttribute> list = pmsProductAttributeApi.getMsgByAttributeIds(attributeIds);
            dto.setProductAttributeList(list);
            return dto;
        }).collect(Collectors.toList());*/
        return null;
    }

    /**
     * 3.获取单个商品属性分类信息
     * @param id
     * @return
     */
    public PmsProductAttributeCategory getMsgById(Long id) {
        return pmsProductAttributeCategoryApi.getMsgById(id);
    }

    /**
     * 4.添加商品属性分类
     * @param name
     * @return
     */
    public void create(String name) {
        Boolean flag = pmsProductAttributeCategoryApi.create(name);
        if (!flag){
            throw new ConsumerException("添加商品属性分类失败");
        }
    }

    /**
     * 5.删除单个商品属性分类
     * @param id
     * @return
     */
    public void deleteById(Integer id) {
        Boolean flag = pmsProductAttributeCategoryApi.deleteById(Convert.toLong(id));
        if (!flag){
            throw new ConsumerException("删除单个商品属性分类失败");
        }
    }

    /**
     * 6.修改商品属性分类
     * @return
     */
    public void updateById(Integer id, String name) {
        Boolean flag = pmsProductAttributeCategoryApi.updateById(Convert.toLong(id),name);
        if (!flag){
            throw new ConsumerException("修改商品属性分类失败");
        }
    }


}
