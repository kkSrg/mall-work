package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsResourceCategoryApi;
import com.mall.dubbo.mapper.UmsResourceCategoryMapper;
import com.mall.pojo.UmsResource;
import com.mall.pojo.UmsResourceCategory;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsResourceCategoryApiImpl implements UmsResourceCategoryApi {

    @Autowired
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> findAll() {
        LambdaQueryWrapper<UmsResourceCategory> wrapper =new LambdaQueryWrapper<>();
        wrapper.orderByDesc(UmsResourceCategory::getSort);
        return umsResourceCategoryMapper.selectList(wrapper);
    }

    @Override
    public void create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public void delete(Integer id) {
        umsResourceCategoryMapper.deleteById(id);
    }

    @Override
    public void update(Integer id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategoryMapper.updateById(umsResourceCategory);
    }
}
