package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductCategoryApi;
import com.mall.dubbo.mapper.PmsProductCategoryMapper;
import com.mall.pojo.PmsProductCategory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductCategoryApiImpl implements PmsProductCategoryApi {

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

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
