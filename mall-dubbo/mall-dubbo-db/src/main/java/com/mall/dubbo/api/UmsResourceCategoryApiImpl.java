package com.mall.dubbo.api;

import com.mall.api.admin.UmsResourceCategoryApi;
import com.mall.dubbo.mapper.UmsResourceCategoryMapper;
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
        return umsResourceCategoryMapper.selectList(null);
    }
}
