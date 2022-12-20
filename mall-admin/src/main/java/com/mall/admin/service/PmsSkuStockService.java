package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.mall.api.admin.PmsSkuStockApi;
import com.mall.pojo.PmsSkuStock;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsSkuStockService {

    @DubboReference
    private PmsSkuStockApi pmsSkuStockApi;

    /**
     * 1.批量更新库存信息
     * @return
     */
    public Integer updateByPid(Integer pid, List<PmsSkuStock> skuStockList) {
        Integer count = pmsSkuStockApi.updateByPid(Convert.toLong(pid),skuStockList);
        return count;
    }

    /**
     * 2.根据商品id及sku编码模糊搜索sku库存
     * @return
     */
    public List<PmsSkuStock> selectSku(Integer pid) {
        List<PmsSkuStock> list = pmsSkuStockApi.selectSku(Convert.toLong(pid));
        return list;
    }

    public List<PmsSkuStock> selectSkuList(Integer pid, String keyword) {
        return pmsSkuStockApi.selectSkuList(Convert.toLong(pid),keyword);
    }
}
