package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.mall.CommonPage;
import com.mall.api.admin.UmsAdminApi;
import com.mall.dto.UmsAdminParam;
import com.mall.exception.ConsumerException;
import com.mall.pojo.Admin;
import com.mall.utils.AppJwtUtil;
import com.mall.vo.AdminVo;
import io.jsonwebtoken.Claims;
import org.apache.dubbo.config.annotation.DubboReference;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsAdminService {

    @DubboReference
    private UmsAdminApi umsAdminApi;


    /**
     * 登录功能
     */
    public String login(String username, String password){

        //将密码加密
        //  String encryptedPwd = MyMD5Util.getEncryptedPwd(password);
        String encryptedPwd = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库
        Admin admin = umsAdminApi.login(username,encryptedPwd);


        if (admin == null){
            //用户名密码错误!!
            throw new ConsumerException("验证输入错误！");
        }else {
            //生成token
            String token = AppJwtUtil.getToken(admin.getId());
            return token;
        }
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     * @param keyword
     * @param page
     * @param pagesize
     */
    public CommonPage<AdminVo> adminsByKw(String keyword, Integer page, Integer pagesize) {
        CommonPage<AdminVo> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);

        List<Admin> list = umsAdminApi.findAll(keyword,page,pagesize);
        List<AdminVo> adminVoList = list.stream().map(admin -> {
            AdminVo vo = new AdminVo();
            BeanUtil.copyProperties(admin,vo);
            vo.setCreateTime(admin.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()):null);
            vo.setLoginTime(admin.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()):null);
            return vo;
        }).collect(Collectors.toList());
        listPage.setList(adminVoList);
        listPage.setTotal(adminVoList.size());
        listPage.setTotalPage((int) (adminVoList.size() / pagesize + ((adminVoList.size() % pagesize == 0) ? 0 : 1)));
        return listPage;
    }

    /**
     * 用户注册
     * @param umsAdminParam
     */
    public Admin register(UmsAdminParam umsAdminParam) {
        return null;
    }


    //解析token
    public Long getToken(String token) {
        Claims claimsBody = AppJwtUtil.getClaimsBody(token);
        int flag = AppJwtUtil.verifyToken(claimsBody);
        //判断token是否在有效期
        if (flag == 1 || flag == 2) {
            throw new ConsumerException("token已失效");
        }
        return Convert.toLong(claimsBody.get("id"));
    }
}
