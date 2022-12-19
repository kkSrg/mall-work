package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsMemberPriceApi;
import com.mall.dubbo.mapper.PmsMemberPriceMapper;
import com.mall.pojo.PmsMemberPrice;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsMemberPriceApiImpl implements PmsMemberPriceApi {

    @Autowired
    private PmsMemberPriceMapper pmsMemberPriceMapper;

    @Override
    public List<PmsMemberPrice> getList(Long id) {
        LambdaQueryWrapper<PmsMemberPrice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsMemberPrice::getProductId,id);
        List<PmsMemberPrice> memberPriceList = pmsMemberPriceMapper.selectList(queryWrapper);
        return memberPriceList;
    }

    @Override
    public void insert(List<PmsMemberPrice> memberPriceList) {
        for (PmsMemberPrice pmsMemberPrice : memberPriceList) {
            pmsMemberPriceMapper.insert(pmsMemberPrice);
        }
    }

    @Override
    public void update(Long id, List<PmsMemberPrice> memberPriceList) {
        LambdaQueryWrapper<PmsMemberPrice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsMemberPrice::getProductId,id);
        pmsMemberPriceMapper.delete(queryWrapper);
        for (PmsMemberPrice pmsMemberPrice : memberPriceList) {
            pmsMemberPriceMapper.insert(pmsMemberPrice);
        }

    }
}
