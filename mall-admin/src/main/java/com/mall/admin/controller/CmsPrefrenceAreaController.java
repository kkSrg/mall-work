package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.CmsPrefrenceAreaService;
import com.mall.pojo.CmsPrefrenceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {

    @Autowired
    private CmsPrefrenceAreaService cmsPrefrenceAreaService;

    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll(){
       List<CmsPrefrenceArea> list = cmsPrefrenceAreaService.listAll();
        return CommonResult.success(list);
    }

}
