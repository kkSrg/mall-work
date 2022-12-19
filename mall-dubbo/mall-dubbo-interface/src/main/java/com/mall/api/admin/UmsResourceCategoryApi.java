package com.mall.api.admin;

import com.mall.pojo.UmsResource;
import com.mall.pojo.UmsResourceCategory;

import java.util.List;

public interface UmsResourceCategoryApi {
    List<UmsResourceCategory> findAll();

    void create(UmsResourceCategory umsResourceCategory);

    void delete(Integer id);

    void update(Integer id, UmsResourceCategory umsResourceCategory);
}
