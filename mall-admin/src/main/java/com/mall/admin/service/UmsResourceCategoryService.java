package com.mall.admin.service;

import com.mall.api.admin.UmsResourceCategoryApi;
import com.mall.pojo.UmsResource;
import com.mall.pojo.UmsResourceCategory;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceCategoryService {


    @DubboReference
    private UmsResourceCategoryApi umsResourceCategoryApi;

    /**
     * 查询所有后台资源分类
     */
    public List<UmsResourceCategory> listAll() {
        return umsResourceCategoryApi.findAll();
    }

    /**
     * 添加后台资源分类
     * @param umsResourceCategory
     */
    public void create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategoryApi.create(umsResourceCategory);
    }

    /**
     * 根据ID删除后台资源
     * @param id
     */
    public void delete(Integer id) {
        umsResourceCategoryApi.delete(id);
    }

    /**
     * 修改后台资源分类
     * @param id
     * @param umsResourceCategory
     */
    public void update(Integer id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategoryApi.update(id,umsResourceCategory);
    }
}
