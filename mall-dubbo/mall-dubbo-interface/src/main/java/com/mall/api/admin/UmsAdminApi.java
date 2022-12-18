package com.mall.api.admin;

import com.mall.pojo.Admin;
import com.mall.vo.AdminVo;

import java.util.List;

public interface UmsAdminApi {
    List<Admin> findAll(String keyword, Integer page, Integer pagesize);

    Admin login(String username, String encryptedPwd);

    //根据id查
    Admin findById(Long id);

    //修改账号状态
    void updateStatus(Long adminId, Integer status);

    //修改指定用户信息
    void updateInfo(Long adminId, Admin admin);

    //注册用户
    Admin save(Admin admin);

    //删除指定用户信息
    void delete(Long adminId);
}
