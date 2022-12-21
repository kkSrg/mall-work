package com.mall.dubbo.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsHomeNewProductServiceApi;
import com.mall.dubbo.mapper.SmsHomeNewProductMapper;
import com.mall.pojo.SmsHomeNewProduct;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 首页新品
 *
 * @author 吴一飞
 * @date 2022/12/18 18:23
 */
@DubboService
public class SmsHomeNewProductServiceApiImpl implements SmsHomeNewProductServiceApi {

    @Autowired
    private SmsHomeNewProductMapper smsHomeNewProductMapper;

    /**
     * 分页查询首页新品
     */
    @Override
    public IPage<SmsHomeNewProduct> page(Integer pageNum, Integer pageSize, String productName, Integer recommendStatus) {
        //创建分页对象
        IPage<SmsHomeNewProduct> page = new Page<>(pageNum, pageSize);
        //设置条件对象
        LambdaQueryWrapper<SmsHomeNewProduct> wrapper = new LambdaQueryWrapper<>();
        //设置条件
        wrapper.like(productName != null, SmsHomeNewProduct::getProductName, productName);
        wrapper.like(recommendStatus != null, SmsHomeNewProduct::getRecommendStatus, recommendStatus);
        wrapper.orderByDesc(SmsHomeNewProduct::getSort);
        //查询分页
        smsHomeNewProductMapper.selectPage(page, wrapper);
        //返回
        return page;

    }

    /**
     * 批量删除
     */
    @Override
    public void deletes(List<Integer> list) {
        //调用持久层删除
        smsHomeNewProductMapper.deleteBatchIds(list);
    }

    /**
     * 批量修改首页新品状态
     */
    @Override
    public void updates(Integer recommendStatus, int[] ids) {
        //创建对象接收数据
        SmsHomeNewProduct smsHomeNewProduct = new SmsHomeNewProduct();
        //遍历获取每一个id
        for (int i = 0; i < ids.length; i++) {
            smsHomeNewProduct.setId(Convert.toLong(ids[i]));
            smsHomeNewProduct.setRecommendStatus(recommendStatus);
            //调用持久层修改
            smsHomeNewProductMapper.updateById(smsHomeNewProduct);
        }
    }
    /**
     * 修改首页新品排序
     */
    @Override
    public void update(SmsHomeNewProduct smsHomeNewProduct) {
        //调用持久层修改
        smsHomeNewProductMapper.updateById(smsHomeNewProduct);
    }

    @Override
    public void insert(SmsHomeNewProduct smsHomeNewProduct) {
        smsHomeNewProductMapper.insert(smsHomeNewProduct);
    }
}
