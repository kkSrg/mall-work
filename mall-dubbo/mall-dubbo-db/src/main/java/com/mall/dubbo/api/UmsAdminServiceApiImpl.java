package com.mall.dubbo.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.sso.UmsAdminServiceApi;
import com.mall.dubbo.mapper.UmsAdminMapper;
import com.mall.pojo.Admin;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class UmsAdminServiceApiImpl implements UmsAdminServiceApi {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    /**
     * 登录功能
     */
    @Override
    public Admin login(String username, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername,username).eq(Admin::getPassword,password);

        return umsAdminMapper.selectOne(wrapper);
    }
}
