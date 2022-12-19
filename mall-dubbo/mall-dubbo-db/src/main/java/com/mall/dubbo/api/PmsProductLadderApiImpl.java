package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductLadderApi;
import com.mall.dubbo.mapper.PmsProductLadderMapper;
import com.mall.pojo.PmsProductLadder;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductLadderApiImpl implements PmsProductLadderApi {

    @Autowired
    private PmsProductLadderMapper pmsProductLadderMapper;

    @Override
    public List<PmsProductLadder> getList(Long id) {
        LambdaQueryWrapper<PmsProductLadder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductLadder::getProductId,id);
        return pmsProductLadderMapper.selectList(queryWrapper);
    }

    @Override
    public void insert(List<PmsProductLadder> productLadderList) {
        for (PmsProductLadder pmsProductLadder : productLadderList) {
            pmsProductLadderMapper.insert(pmsProductLadder);
        }
    }

    @Override
    public void update(Long id, List<PmsProductLadder> productLadderList) {
        LambdaQueryWrapper<PmsProductLadder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductLadder::getProductId,id);
        pmsProductLadderMapper.delete(queryWrapper);
        for (PmsProductLadder pmsProductLadder : productLadderList) {
            pmsProductLadderMapper.insert(pmsProductLadder);
        }
    }
}
