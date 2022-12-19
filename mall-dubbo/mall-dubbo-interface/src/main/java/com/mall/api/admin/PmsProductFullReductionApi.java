package com.mall.api.admin;

import com.mall.pojo.PmsProductFullReduction;

import java.util.List;

public interface PmsProductFullReductionApi {
    //根据商品id获取信息
    List<PmsProductFullReduction> getList(Long id);

    void insert(List<PmsProductFullReduction> productFullReductionList);

    void update(Long id, List<PmsProductFullReduction> productFullReductionList);
}
