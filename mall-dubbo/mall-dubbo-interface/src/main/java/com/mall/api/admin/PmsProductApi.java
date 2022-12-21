package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.PmsProduct;

import java.util.List;

public interface PmsProductApi {
    IPage<PmsProduct> getList(PmsProductListDto pmsProductListDto, Integer page, Integer pagesize);

    //根据商品名称或货号模糊查询
    List<PmsProduct> simpleList(String keyword);

    //根据商品id查询商品信息
    PmsProduct getMsgById(Long id);

    void insert(PmsProduct pmsProduct);

    void update(Long id, PmsProduct pmsProduct);

    //批量修改删除状态
    void deleteStatus(List<Long> idList,Integer deleteStatus);

    //批量设为新品
    void newStatus(List<Long> idList, Integer newStatus);

    //批量上下架商品
    void publishStatus(List<Long> idList, Integer publishStatus);

    //批量推荐商品
    void recommendStatus(List<Long> idList, Integer recommendStatus);

    //批量修改审核状态
    void verifyStatus(String detail, List<Long> idList, Integer verifyStatus);

    PmsProduct selectById(Long productId);
}
