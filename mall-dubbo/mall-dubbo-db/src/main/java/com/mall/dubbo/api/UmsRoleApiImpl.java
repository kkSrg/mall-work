package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsRoleApi;
import com.mall.dubbo.mapper.UmsRoleMapper;
import com.mall.pojo.UmsRole;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsRoleApiImpl implements UmsRoleApi {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsRole> findAll() {
        LambdaQueryWrapper<UmsRole> wrapper = new LambdaQueryWrapper<>();
        List<UmsRole> list =umsRoleMapper.selectList(wrapper);

        return list;
    }
}
