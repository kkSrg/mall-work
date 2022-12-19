package com.mall.api.admin;

import com.mall.pojo.PmsProductLadder;

import java.util.List;

public interface PmsProductLadderApi {
    //根据商品id获取信息
    List<PmsProductLadder> getList(Long id);

    void insert(List<PmsProductLadder> productLadderList);

    void update(Long id, List<PmsProductLadder> productLadderList);
}
