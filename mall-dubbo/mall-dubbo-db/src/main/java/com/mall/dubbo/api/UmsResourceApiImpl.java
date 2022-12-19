package com.mall.dubbo.api;

import com.mall.api.admin.UmsResourceApi;
import com.mall.dubbo.mapper.UmsResourceMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UmsResourceApiImpl implements UmsResourceApi {

    @Autowired
    private UmsResourceMapper umsResourceMapper;


}
