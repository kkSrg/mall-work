package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.SmsHomeNewProduct;

import java.util.List;

/**
 * 首页新品
 *
 * @author 吴一飞
 * @date 2022/12/18 18:23
 */
public interface SmsHomeNewProductServiceApi {

    /**
     * 分页查询首页新品
     */
    IPage<SmsHomeNewProduct> page(Integer pageNum, Integer pageSize, String productName, Integer recommendStatus);

    /**
     * 根据id批量删除
     */
    void deletes(List<Integer> list);

    /**
     * 批量修改首页新品状态
     */
    void updates(Integer recommendStatus, int[] ids);

    /**
     * 修改新品
     */
    void update(SmsHomeNewProduct smsHomeNewProduct);

    void insert(SmsHomeNewProduct smsHomeNewProduct);

}
