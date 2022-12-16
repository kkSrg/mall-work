package com.mall.controller;


import com.mall.dto.UmsAdminLoginParam;
import com.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * 用户功能
 *
 * @author 吴一飞
 * @date 2022/12/15 22:01
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录功能
     * @param umsAdminLoginParam
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        try {
            //获取用户密码
            String username = umsAdminLoginParam.getUsername();
            String password = umsAdminLoginParam.getPassword();

            //请求service方法
            String token = adminService.login(username, password);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
