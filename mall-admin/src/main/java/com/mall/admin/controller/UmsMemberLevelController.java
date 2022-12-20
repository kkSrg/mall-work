package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.UmsMemberLevelService;
import com.mall.pojo.UmsMemberLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService umsMemberLevelService;

    /**
     * 查询所有会员等级
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<UmsMemberLevel>> list(Integer defaultStatus){
        List<UmsMemberLevel> list = umsMemberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }
}
