package com.mall.api.sso;


import com.mall.pojo.Admin;

public interface UmsAdminServiceApi {

    /**
     * 登录功能
     */
    Admin login(String username, String password);
}
