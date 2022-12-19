package com.mall.api.admin;

import com.mall.pojo.PmsSkuStock;

import java.util.List;

public interface PmsSkuStockApi {

    //根据商品id及sku编码模糊搜索sku库存
    List<PmsSkuStock> selectSku(Long pid, String keyword);

    //批量更新库存信息
    Integer updateByPid(Long toLong, List<PmsSkuStock> skuStockList);

    //根据商品id获取信息
    List<PmsSkuStock> getList(Long id);

    void insert(List<PmsSkuStock> skuStockList);

    void update(Long id, List<PmsSkuStock> skuStockList);
}
