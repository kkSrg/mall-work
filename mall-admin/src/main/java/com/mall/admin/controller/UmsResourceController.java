package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsResourceService;
import com.mall.pojo.UmsResource;
import com.mall.pojo.UmsResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService umsResourceService;

    /**
     * 分页模糊查询后台资源
     * @param page
     * @param pagesize
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @return
     */
    @GetMapping("list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pagesize,
                                                      Integer categoryId,String nameKeyword, String urlKeyword){
        CommonPage<UmsResource> result = umsResourceService.getPage(page,pagesize,categoryId,nameKeyword,urlKeyword);
        return CommonResult.success(result);
    }

    /**
     * 查询所有后台资源
     * @return
     */
    @GetMapping("listAll")
    public CommonResult<List<UmsResource>> listAll(){
        List<UmsResource> result = umsResourceService.listAll();
        return CommonResult.success(result);
    }
}
