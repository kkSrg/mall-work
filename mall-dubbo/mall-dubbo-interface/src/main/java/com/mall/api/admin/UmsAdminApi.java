package com.mall.api.admin;

import com.mall.pojo.Admin;

import java.util.List;

public interface UmsAdminApi {
    List<Admin> findAll(String keyword, Integer page, Integer pagesize);

    Admin login(String username, String encryptedPwd);

    //根据id查
    Admin findById(Long id);
}
