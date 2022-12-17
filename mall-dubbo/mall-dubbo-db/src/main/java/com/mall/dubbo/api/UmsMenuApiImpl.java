package com.mall.dubbo.api;

import com.mall.api.admin.UmsMenuApi;
import com.mall.dubbo.mapper.UmsMenuMapper;
import com.mall.pojo.UmsMenu;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMenuApiImpl implements UmsMenuApi {

    @Autowired
    private UmsMenuMapper umsMenuMapper;

    @Override
    public List<UmsMenu> findAll() {
        return umsMenuMapper.selectList(null);
    }
}
