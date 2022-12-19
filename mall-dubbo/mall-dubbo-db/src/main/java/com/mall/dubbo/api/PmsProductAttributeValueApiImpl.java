package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductAttributeValueApi;
import com.mall.dubbo.mapper.PmsProductAttributeValueMapper;
import com.mall.pojo.PmsProductAttributeValue;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductAttributeValueApiImpl implements PmsProductAttributeValueApi {

    @Autowired
    private PmsProductAttributeValueMapper pmsProductAttributeValueMapper;

    @Override
    public List<PmsProductAttributeValue> getList(Long id) {
        LambdaQueryWrapper<PmsProductAttributeValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductAttributeValue::getProductId,id);
        List<PmsProductAttributeValue> pmsProductAttributeValues = pmsProductAttributeValueMapper.selectList(queryWrapper);
        return pmsProductAttributeValues;
    }

    @Override
    public void insert(List<PmsProductAttributeValue> productAttributeValueList) {
        for (PmsProductAttributeValue pmsProductAttributeValue : productAttributeValueList) {
            pmsProductAttributeValueMapper.insert(pmsProductAttributeValue);
        }
    }

    @Override
    public void update(Long id, List<PmsProductAttributeValue> productAttributeValueList) {
        LambdaQueryWrapper<PmsProductAttributeValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProductAttributeValue::getProductId,id);
        pmsProductAttributeValueMapper.delete(queryWrapper);
        for (PmsProductAttributeValue pmsProductAttributeValue : productAttributeValueList) {
            pmsProductAttributeValueMapper.insert(pmsProductAttributeValue);
        }
    }
}
