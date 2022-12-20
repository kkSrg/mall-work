package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsSkuStockApi;
import com.mall.dubbo.mapper.PmsSkuStockMapper;
import com.mall.pojo.PmsSkuStock;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsSkuStockApiImpl implements PmsSkuStockApi {

    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    //根据商品id及sku编码模糊搜索sku库存
    @Override
    public List<PmsSkuStock> selectSku(Long pid, String keyword) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=pid,PmsSkuStock::getProductId,pid).or().like(PmsSkuStock::getSkuCode,keyword);
        List<PmsSkuStock> pmsSkuStockList = pmsSkuStockMapper.selectList(queryWrapper);
        return pmsSkuStockList;
    }


    //批量更新库存信息
    @Override
    public Integer updateByPid(Long pid, List<PmsSkuStock> skuStockList) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=pid,PmsSkuStock::getProductId,pid);
        int count = 0;
        for (PmsSkuStock pmsSkuStock : skuStockList) {
            count = pmsSkuStockMapper.update(pmsSkuStock, queryWrapper);
            count++;
        }
        return count;
    }

    @Override
    public List<PmsSkuStock> getList(Long id) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsSkuStock::getProductId,id);
        return pmsSkuStockMapper.selectList(queryWrapper);
    }

    @Override
    public void insert(List<PmsSkuStock> skuStockList) {
        for (PmsSkuStock pmsSkuStock : skuStockList) {
            pmsSkuStockMapper.insert(pmsSkuStock);
        }
    }

    @Override
    public void update(Long id, List<PmsSkuStock> skuStockList) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsSkuStock::getProductId,id);
        pmsSkuStockMapper.delete(queryWrapper);
        for (PmsSkuStock pmsSkuStock : skuStockList) {
            pmsSkuStockMapper.insert(pmsSkuStock);
        }
    }
}
