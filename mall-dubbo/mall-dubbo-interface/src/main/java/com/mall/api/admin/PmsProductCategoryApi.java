package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.PmsProductCategory;

import java.util.List;

public interface PmsProductCategoryApi {
    List<PmsProductCategory> listWithParent();

    List<PmsProductCategory> listWithChildren(long id);

    IPage<PmsProductCategory> findByParentId(Integer parentId, Integer pageNum, Integer pageSize);

    //根据id获取商品分类
    PmsProductCategory getMsgById(Long toLong);

    //添加商品分类
    void create(PmsProductCategory category);

    //删除商品分类
    void deleteById(Long toLong);

    //修改商品分类
    void updateById(Long id, PmsProductCategory category);

    //修改导航栏显示状态
    void updateNavStatus(List<Long> idList, Integer navStatus);

    //修改显示状态
    void updateShowStatus(List<Long> idList, Integer showStatus);
}
