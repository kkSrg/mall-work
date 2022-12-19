package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.UmsResource;

import java.util.Map;

public interface UmsResourceApi {

    IPage<UmsResource> getList(Integer categoryId, String nameKeyword, String urlKeyword, Integer page, Integer pagesize);
}
