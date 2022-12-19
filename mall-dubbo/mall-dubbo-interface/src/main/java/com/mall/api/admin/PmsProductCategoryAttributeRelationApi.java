package com.mall.api.admin;

import java.util.List;

public interface PmsProductCategoryAttributeRelationApi {

    List<Long> selectAttributeId(Integer cid);
}
