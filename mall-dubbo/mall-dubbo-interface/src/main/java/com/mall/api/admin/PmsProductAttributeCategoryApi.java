package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.PmsProductAttributeCategory;

import java.util.List;

public interface PmsProductAttributeCategoryApi {
    //数据总条数
    Integer selectCount();

    //获取所有商品属性分类
    IPage findAllProductAttributeCategory(Integer pageNum, Integer pageSize);

    //获取单个商品属性分类信息
    PmsProductAttributeCategory getMsgById(Long id);

    //添加商品属性分类
    Boolean create(String name);

    //删除单个商品属性分类
    Boolean deleteById(Long id);

    //修改商品属性分类
    Boolean updateById(Long toLong, String name);
}
