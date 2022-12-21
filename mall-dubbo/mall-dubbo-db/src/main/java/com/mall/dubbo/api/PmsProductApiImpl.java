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

    //根据商品名称或货号模糊查询
    @Override
    public List<PmsProduct> simpleList(String keyword) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(PmsProduct::getName,keyword);
        queryWrapper.like(PmsProduct::getProductSn,keyword);
        List<PmsProduct> pmsProducts = pmsProductMapper.selectList(queryWrapper);
        return pmsProducts;
    }

    //根据商品id查询商品信息
    @Override
    public PmsProduct getMsgById(Long id) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProduct::getId,id);
        PmsProduct pmsProduct = pmsProductMapper.selectOne(queryWrapper);
        return pmsProduct;
    }

    @Override
    public void insert(PmsProduct pmsProduct) {
        pmsProductMapper.insert(pmsProduct);
    }

    @Override
    public void update(Long id, PmsProduct pmsProduct) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=id,PmsProduct::getId,id);
        pmsProductMapper.update(pmsProduct,queryWrapper);
    }

    @Override
    public void deleteStatus(List<Long> idList,Integer deleteStatus) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProduct::getId,idList);
        pmsProductMapper.delete(queryWrapper);

    }

    @Override
    public void newStatus(List<Long> idList, Integer newStatus) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProduct::getId,idList);
        List<PmsProduct> productList = pmsProductMapper.selectList(queryWrapper);
        for (PmsProduct pmsProduct : productList) {
            pmsProduct.setNewStatus(newStatus);
            LambdaQueryWrapper<PmsProduct> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsProduct::getId,pmsProduct.getId());
            pmsProductMapper.update(pmsProduct,queryWrapper1);
        }
    }

    @Override
    public void publishStatus(List<Long> idList, Integer publishStatus) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProduct::getId,idList);
        List<PmsProduct> productList = pmsProductMapper.selectList(queryWrapper);
        for (PmsProduct pmsProduct : productList) {
            pmsProduct.setPublishStatus(publishStatus);
            LambdaQueryWrapper<PmsProduct> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsProduct::getId,pmsProduct.getId());
            pmsProductMapper.update(pmsProduct,queryWrapper1);
        }
    }

    @Override
    public void recommendStatus(List<Long> idList, Integer recommendStatus) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProduct::getId,idList);
        List<PmsProduct> productList = pmsProductMapper.selectList(queryWrapper);
        for (PmsProduct pmsProduct : productList) {
            pmsProduct.setRecommandStatus(recommendStatus);
            LambdaQueryWrapper<PmsProduct> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsProduct::getId,pmsProduct.getId());
            pmsProductMapper.update(pmsProduct,queryWrapper1);
        }
    }

    @Override
    public void verifyStatus(String detail, List<Long> idList, Integer verifyStatus) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsProduct::getId,idList);
        List<PmsProduct> productList = pmsProductMapper.selectList(queryWrapper);
        for (PmsProduct pmsProduct : productList) {
            pmsProduct.setVerifyStatus(verifyStatus);
            pmsProduct.setDetailTitle(detail);
            pmsProduct.setDetailDesc(detail);
            pmsProduct.setDetailHtml(detail);
            pmsProduct.setDetailMobileHtml(detail);
            LambdaQueryWrapper<PmsProduct> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsProduct::getId,pmsProduct.getId());
            pmsProductMapper.update(pmsProduct,queryWrapper1);
        }
    }
}
