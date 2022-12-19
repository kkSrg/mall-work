package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.CmsSubjectService;
import com.mall.pojo.CmsSubject;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService cmsSubjectService;
    //分页查询
    @GetMapping("/list")
    public CommonResult<CommonPage<CmsSubject>> list(@RequestParam(value = "keyword",required = false)String keyword,
                                                     @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
        CommonPage<CmsSubject>  result = cmsSubjectService.list(keyword, pageSize, pageNum);
        return CommonResult.success(result);
    }
}
