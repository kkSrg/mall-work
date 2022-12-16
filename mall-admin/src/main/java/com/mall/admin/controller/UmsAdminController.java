package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsAdminService;
import com.mall.dto.UmsAdminLoginParam;
import com.mall.dto.UmsAdminParam;
import com.mall.pojo.Admin;
import com.mall.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    /**
     * 登录功能
     * @param umsAdminLoginParam
     * @return
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        //获取用户密码
        String username = umsAdminLoginParam.getUsername();
        String password = umsAdminLoginParam.getPassword();

        //请求service方法
        String token = umsAdminService.login(username,password);

        return CommonResult.success(token);
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     * @param keyword
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("list")
    public CommonResult<CommonPage<AdminVo>> list(String keyword,
                                                  @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pagesize){
        CommonPage<AdminVo> result = umsAdminService.adminsByKw(keyword,page,pagesize);
        return CommonResult.success(result);
    }

    /**
     * 用户注册
     * @param umsAdminParam
     * @return
     */
    @PostMapping("register")
    public CommonResult<Admin> register(@RequestBody UmsAdminParam umsAdminParam){
        Admin admin = umsAdminService.register(umsAdminParam);
        return CommonResult.success(admin);
    }
}
