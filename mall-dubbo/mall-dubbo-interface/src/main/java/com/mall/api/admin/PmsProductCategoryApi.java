package com.mall.api.admin;

import com.mall.pojo.PmsProductCategory;

import java.util.List;

public interface PmsProductCategoryApi {
    List<PmsProductCategory> listWithParent();

    List<PmsProductCategory> listWithChildren(long id);
}
