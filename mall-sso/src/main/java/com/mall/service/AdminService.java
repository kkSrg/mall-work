package com.mall.service;


import com.mall.api.sso.UmsAdminServiceApi;
import com.mall.pojo.Admin;
import com.mall.utils.AppJwtUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {


    @DubboReference
    private UmsAdminServiceApi umsAdminServiceApi;


    /**
     * 登录功能
     */
    public String login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //将密码加密
      //  String encryptedPwd = MyMD5Util.getEncryptedPwd(password);
        String encryptedPwd = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库
        Admin admin = umsAdminServiceApi.login(username,encryptedPwd);


        if (admin == null){
            //用户名密码错误!!
            throw new RuntimeException("验证输入错误！");
        }else {
            //生成token
            String token = AppJwtUtil.getToken(admin.getId());
            return token;
        }
    }
}
