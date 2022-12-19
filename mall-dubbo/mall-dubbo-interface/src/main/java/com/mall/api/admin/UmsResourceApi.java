package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.UmsResource;

import java.util.List;
import java.util.Map;

public interface UmsResourceApi {

    IPage<UmsResource> getList(Integer categoryId, String nameKeyword, String urlKeyword, Integer page, Integer pagesize);

    //根据资源id查资源
    List<UmsResource> findByIds(List<Long> resourceIds);

    //查询所有资源
    List<UmsResource> findAll();

    void create(UmsResource umsResource);

    void delete(Integer id);

    void update(UmsResource umsResource);
}
