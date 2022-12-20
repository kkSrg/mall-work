package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.PmsProductAttributeCategoryApi;
import com.mall.vo.PmsProductAttributeCategoryVo;
import com.mall.dubbo.mapper.PmsProductAttributeCategoryMapper;
import com.mall.pojo.PmsProductAttributeCategory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductAttributeCategoryApiImpl implements PmsProductAttributeCategoryApi {

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    //数据总条数
    @Override
    public Integer selectCount() {
        return pmsProductAttributeCategoryMapper.selectCount(null);
    }

    //获取所有商品属性分类
    @Override
    public IPage findAllProductAttributeCategory(Integer pageNum, Integer pageSize) {
        IPage<PmsProductAttributeCategory> pg=new Page<>(pageNum,pageSize);
        return pmsProductAttributeCategoryMapper.selectPage(pg,null);
    }

    //获取单个商品属性分类信息
    @Override
    public PmsProductAttributeCategory getMsgById(Long id) {
        LambdaQueryWrapper<PmsProductAttributeCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductAttributeCategory::getId,id);
        return pmsProductAttributeCategoryMapper.selectOne(queryWrapper);
    }

    //添加商品属性分类
    @Override
    public Boolean create(String name) {
        PmsProductAttributeCategory category = new PmsProductAttributeCategoryVo();
        category.setName(name);
        //TODO: category的属性数量和参数数量应该查数据库正确赋值(但此处分类数据尚未插入,可能不要添加下面两个数据)
        category.setAttributeCount(0);
        category.setParamCount(0);

        int count = pmsProductAttributeCategoryMapper.insert(category);
        return count != 0;
    }

    //删除单个商品属性分类
    @Override
    public Boolean deleteById(Long id) {
        int count = pmsProductAttributeCategoryMapper.deleteById(id);
        return count != 0;
    }

    //修改商品属性分类
    @Override
    public Boolean updateById(Long id, String name) {
        LambdaQueryWrapper<PmsProductAttributeCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductAttributeCategory::getId,id);
        PmsProductAttributeCategory category = pmsProductAttributeCategoryMapper.selectOne(queryWrapper);
        category.setName(name);
        int count = pmsProductAttributeCategoryMapper.update(category, queryWrapper);
        return count != 0;
    }
}
