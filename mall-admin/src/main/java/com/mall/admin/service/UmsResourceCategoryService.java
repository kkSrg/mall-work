package com.mall.admin.service;

import com.mall.api.admin.UmsResourceCategoryApi;
import com.mall.pojo.UmsResourceCategory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceCategoryService {


    @DubboReference
    private UmsResourceCategoryApi umsResourceCategoryApi;

    public List<UmsResourceCategory> listAll() {
        return umsResourceCategoryApi.findAll();
    }
}
