package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.SmsHomeRecommendProductApi;
import com.mall.dubbo.mapper.SmsHomeRecommendProductMapper;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsHomeRecommendProductApiImpl implements SmsHomeRecommendProductApi {

    @Autowired
    private SmsHomeRecommendProductMapper smsHomeRecommendProductMapper;

    @Override
    public List<SmsHomeRecommendProduct> list(String productName,//产品名称
                                              Integer recommendStatus, //推荐状态
                                              Integer pageSize,//页码大小
                                              Integer pageNum)//当前页码
    {
        IPage<SmsHomeRecommendProduct> pg = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<SmsHomeRecommendProduct> queryWrapper = new LambdaQueryWrapper<>();
        //查询全部按照sort升序
        queryWrapper.orderByAsc(SmsHomeRecommendProduct::getSort);

        //如果keyword存在则有条件查询，模糊查询
        queryWrapper.like(StrUtil.isNotBlank(productName), SmsHomeRecommendProduct::getProductName, productName);
        queryWrapper.like(recommendStatus!=null, SmsHomeRecommendProduct::getRecommendStatus, recommendStatus);

        smsHomeRecommendProductMapper.selectPage(pg,queryWrapper);

        return pg.getRecords();

    }

    @Override
    public Integer finTotal(String productName, Integer recommendStatus) {
        LambdaQueryWrapper<SmsHomeRecommendProduct> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(StrUtil.isNotBlank(productName), SmsHomeRecommendProduct::getProductName, productName);
        queryWrapper.like(null != recommendStatus, SmsHomeRecommendProduct::getRecommendStatus, recommendStatus);
        Integer count = smsHomeRecommendProductMapper.selectCount(queryWrapper);
        return count;
    }

    @Override
    public int deleteIds(List<Long> ids) {
        int count = 0;
        for (Long id : ids) {
            smsHomeRecommendProductMapper.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public int updateCommend(List<Long> ids, Integer recommendStatus) {
        int count = 0;
        LambdaQueryWrapper<SmsHomeRecommendProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SmsHomeRecommendProduct::getId, ids);
        List<SmsHomeRecommendProduct> smsHomeRecommendProductList = smsHomeRecommendProductMapper.selectList(queryWrapper);
        for (SmsHomeRecommendProduct smsHomeRecommendProduct : smsHomeRecommendProductList) {
            smsHomeRecommendProduct.setRecommendStatus(recommendStatus);
            smsHomeRecommendProductMapper.updateById(smsHomeRecommendProduct);
            count++;
        }

        return count;
    }

    @Override
    public void updateSort(Integer id, Integer sort) {
        SmsHomeRecommendProduct smsHomeRecommendProduct = new SmsHomeRecommendProduct();
        SmsHomeRecommendProduct IsmsHomeRecommendProduct = smsHomeRecommendProductMapper.selectById(id);
        smsHomeRecommendProduct.setId(id);
        smsHomeRecommendProduct.setSort(sort);
        smsHomeRecommendProduct.setRecommendStatus(IsmsHomeRecommendProduct.getRecommendStatus());
        smsHomeRecommendProductMapper.updateById(smsHomeRecommendProduct);
    }

    //首页添加新品
    @Override
    public void create(List<SmsHomeRecommendProduct> homeNewProductList) {
        for (SmsHomeRecommendProduct smsHomeNewProduct : homeNewProductList) {
            smsHomeRecommendProductMapper.insert(smsHomeNewProduct);
        }
    }

}
