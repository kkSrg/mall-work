package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsAdminService;
import com.mall.pojo.Admin;
import com.mall.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

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

    @PostMapping("register")
    public CommonResult<Admin> register(){
        Admin admin = new Admin();
        return CommonResult.success(admin);
    }
}
