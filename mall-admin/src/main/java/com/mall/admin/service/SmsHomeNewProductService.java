package com.mall.admin.service;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.SmsHomeNewProductServiceApi;
import com.mall.pojo.SmsHomeNewProduct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页新品
 *
 * @author 吴一飞
 * @date 2022/12/18 18:23
 */
@Service
public class SmsHomeNewProductService {

    @DubboReference
    private SmsHomeNewProductServiceApi smsHomeNewProductServiceApi;

    /**
     * 分页查询首页新品
     *
     * @return
     */
    public CommonPage<SmsHomeNewProduct> getPage(Integer pageNum, Integer pageSize, String productName, Integer recommendStatus) {
        CommonPage<SmsHomeNewProduct> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);

        IPage<SmsHomeNewProduct> page = smsHomeNewProductServiceApi.page(pageNum, pageSize, productName, recommendStatus);

        result.setTotal(Convert.toInt(page.getTotal()));
        result.setTotalPage(Convert.toInt(page.getPages()));
        result.setList(page.getRecords());

        return  result;
    }

    /**
     * 批量删除首页新品
     * @param ids
     * @return
     */
    public void deletes(int[] ids) {
        List<Integer> list = new ArrayList<>();
        //将数组的内容添加到list中
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        //调用api
        smsHomeNewProductServiceApi.deletes(list);

    }

    /**
     * 批量修改首页新品状态
     * @param recommendStatus
     * @param ids
     */
    public void updates(Integer recommendStatus, int[] ids) {
        smsHomeNewProductServiceApi.updates(recommendStatus, ids);
    }

    /**
     * 修改首页新品排序
     */
    public void updateSort(Long id, Integer sort) {
        //创建对象封装数据
        SmsHomeNewProduct smsHomeNewProduct = new SmsHomeNewProduct();
        smsHomeNewProduct.setId(id);
        smsHomeNewProduct.setSort(sort);

        //远程调用修改方法
        smsHomeNewProductServiceApi.update(smsHomeNewProduct);
    }

    /**
     * 添加首页新品
     *
     * @author 吴一飞
     * @date 2022/12/18 20:25
     */
    public void saveList(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct smsHomeNewProduct : homeNewProductList) {
            //远程调用保存方法
            smsHomeNewProductServiceApi.update(smsHomeNewProduct);
        }
    }
}
