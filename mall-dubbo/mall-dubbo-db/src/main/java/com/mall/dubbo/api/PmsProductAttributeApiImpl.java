package com.mall.dubbo.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.PmsProductAttributeApi;
import com.mall.dubbo.mapper.PmsProductAttributeMapper;
import com.mall.pojo.PmsProductAttribute;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductAttributeApiImpl implements PmsProductAttributeApi {

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;

    //根据产品分类id查找其下的所有属性参数
    @Override
    public List<PmsProductAttribute> selectAllAttributeByCategoryId(long id) {
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductAttribute::getProductAttributeCategoryId,id);
        List<PmsProductAttribute> list = pmsProductAttributeMapper.selectList(queryWrapper);
        return list;
    }
}
