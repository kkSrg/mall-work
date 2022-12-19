package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductFullReductionApi;
import com.mall.dubbo.mapper.PmsProductFullReductionMapper;
import com.mall.pojo.PmsProductFullReduction;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductFullReductionApiImpl implements PmsProductFullReductionApi {

    @Autowired
    private PmsProductFullReductionMapper pmsProductFullReductionMapper;

    @Override
    public List<PmsProductFullReduction> getList(Long id) {
        LambdaQueryWrapper<PmsProductFullReduction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductFullReduction::getProductId,id);
        return pmsProductFullReductionMapper.selectList(queryWrapper);
    }

    @Override
    public void insert(List<PmsProductFullReduction> productFullReductionList) {
        for (PmsProductFullReduction pmsProductFullReduction : productFullReductionList) {
            pmsProductFullReductionMapper.insert(pmsProductFullReduction);
        }
    }

    @Override
    public void update(Long id, List<PmsProductFullReduction> productFullReductionList) {
        LambdaQueryWrapper<PmsProductFullReduction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductFullReduction::getProductId,id);
        pmsProductFullReductionMapper.delete(queryWrapper);
        for (PmsProductFullReduction pmsProductFullReduction : productFullReductionList) {
            pmsProductFullReductionMapper.insert(pmsProductFullReduction);
        }
    }
}
