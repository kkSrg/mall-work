package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface PmsProductCategoryAttributeRelationApi {

    IPage selectAttributeId(Integer pageNum, Integer pageSize,Integer cid);

    List<Long> selectAttributeIds(Integer productCategoryId);
}
