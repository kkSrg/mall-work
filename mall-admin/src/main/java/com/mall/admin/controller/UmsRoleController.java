package com.mall.admin.controller;

import com.mall.admin.service.UmsRoleService;
import com.mall.pojo.UmsRole;
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
    public ResponseEntity<List<UmsRole>> listAll(){
        List<UmsRole> result = umsRoleService.listAll();
        return ResponseEntity.ok(result);
    }
}
