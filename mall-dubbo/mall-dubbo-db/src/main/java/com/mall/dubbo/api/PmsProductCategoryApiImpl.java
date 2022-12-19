package com.mall.dubbo.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductCategoryApi;
import com.mall.dubbo.mapper.PmsProductCategoryMapper;
import com.mall.pojo.PmsBrand;
import com.mall.pojo.PmsProductCategory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductCategoryApiImpl implements PmsProductCategoryApi {

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;


    //根据parentId查询数据
    @Override
    public List<PmsProductCategory> findByParentId(Integer parentId) {
        LambdaQueryWrapper<PmsProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=parentId,PmsProductCategory::getParentId, Convert.toLong(parentId));
        List<PmsProductCategory> categoryList = pmsProductCategoryMapper.selectList(queryWrapper);
        return categoryList;
    }

    //根据id获取商品分类
    @Override
    public PmsProductCategory getMsgById(Long id) {
        PmsProductCategory category = pmsProductCategoryMapper.selectById(id);
        return category;
    }

    //添加商品分类
    @Override
    public void create(PmsProductCategory category) {
        pmsProductCategoryMapper.insert(category);
    }

    //删除商品分类
    @Override
    public void deleteById(Long id) {
        pmsProductCategoryMapper.deleteById(id);
    }

    //修改商品分类
    @Override
    public void updateById(Long id, PmsProductCategory category) {
        LambdaQueryWrapper<PmsProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != id,PmsProductCategory::getId,id);
        pmsProductCategoryMapper.update(category,queryWrapper);
    }

    //修改导航栏显示状态
    @Override
    public void updateNavStatus(List<Long> idList, Integer navStatus) {
        LambdaQueryWrapper<PmsProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProductCategory::getId,idList);
        List<PmsProductCategory> categoryList = pmsProductCategoryMapper.selectList(queryWrapper);
        for (PmsProductCategory category : categoryList) {
            category.setNavStatus(navStatus);
            pmsProductCategoryMapper.updateById(category);
        }
    }

    //修改显示状态
    @Override
    public void updateShowStatus(List<Long> idList, Integer showStatus) {
        LambdaQueryWrapper<PmsProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProductCategory::getId,idList);
        List<PmsProductCategory> categoryList = pmsProductCategoryMapper.selectList(queryWrapper);
        for (PmsProductCategory category : categoryList) {
            category.setShowStatus(showStatus);
            pmsProductCategoryMapper.updateById(category);
        }
    }

    @Override
    public List<PmsProductCategory> listWithParent() {
        LambdaQueryWrapper<PmsProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProductCategory::getLevel,0);
        List<PmsProductCategory> pmsProductCategorieParent = pmsProductCategoryMapper.selectList(wrapper);
        return pmsProductCategorieParent;
    }

    @Override
    public List<PmsProductCategory> listWithChildren(long id) {
        LambdaQueryWrapper<PmsProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProductCategory::getParentId,id);
        List<PmsProductCategory> pmsProductCategorieChildren = pmsProductCategoryMapper.selectList(wrapper);
        return pmsProductCategorieChildren;
    }
}
