package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.UmsRoleService;
import com.mall.pojo.UmsRole;
import com.mall.vo.UmsRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;

    /**
     * 获取所有角色
     * @return
     */
    @GetMapping("listAll")
    public CommonResult<List<UmsRoleVo>> listAll(){
        List<UmsRoleVo> result = umsRoleService.listAll();
        return CommonResult.success(result);
    }
}
