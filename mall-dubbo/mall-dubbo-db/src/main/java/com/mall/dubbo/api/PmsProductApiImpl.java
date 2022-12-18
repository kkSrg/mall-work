package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.PmsProductApi;
import com.mall.dto.PmsProductListDto;
import com.mall.dubbo.mapper.PmsProductMapper;
import com.mall.pojo.Admin;
import com.mall.pojo.PmsProduct;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductApiImpl implements PmsProductApi {

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override
    public IPage<PmsProduct> getList(PmsProductListDto pmsProductListDto, Integer page, Integer pagesize) {
        //创建分页对象，设置分页参数
        //注意：使用分页，需要配置分页插件
        IPage<PmsProduct> pg=new Page<>(page,pagesize);
        LambdaQueryWrapper<PmsProduct> wrapper =new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PmsProduct::getSort);
        wrapper.eq(pmsProductListDto.getBrandId()!=null,PmsProduct::getBrandId,pmsProductListDto.getBrandId());
        wrapper.eq(pmsProductListDto.getProductSn()!=null,PmsProduct::getProductSn,pmsProductListDto.getProductSn());
        wrapper.eq(pmsProductListDto.getProductCategoryId()!=null,PmsProduct::getProductCategoryId,pmsProductListDto.getProductCategoryId());
        wrapper.eq(pmsProductListDto.getPublishStatus()!=null,PmsProduct::getPublishStatus,pmsProductListDto.getPublishStatus());
        wrapper.eq(pmsProductListDto.getVerifyStatus()!=null,PmsProduct::getVerifyStatus,pmsProductListDto.getVerifyStatus());
        wrapper.like(pmsProductListDto.getKeyword()!=null,PmsProduct::getName,pmsProductListDto.getKeyword());
        pmsProductMapper.selectPage(pg,wrapper);
        return pg;
    }
}
