package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsMenuService;
import com.mall.pojo.UmsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    /**
     * 分页查询后台菜单
     * @param parentId
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Integer parentId,
                                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pagesize){
        CommonPage<UmsMenu> result = umsMenuService.list(parentId,page,pagesize);
        return CommonResult.success(result);
    }

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     * @return
     */
    @PostMapping("updateHidden/{id}")
    public CommonResult updateHidden(@PathVariable Integer id,Integer hidden){
        umsMenuService.updateHidden(id,hidden);
        return CommonResult.success(1);
    }

    /**
     * 根据ID获取菜单详情
     * @param id
     * @return
     */
    @GetMapping("{id}")
    private CommonResult<UmsMenu> info(@PathVariable Integer id){
        UmsMenu umsMenu = umsMenuService.findById(id);
        return CommonResult.success(umsMenu);
    }

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    @PostMapping("update/{id}")
    public CommonResult update(@PathVariable Integer id,@RequestBody UmsMenu umsMenu){
        umsMenuService.update(id,umsMenu);
        return CommonResult.success(1);
    }

    /**
     * 添加后台菜单
     * @param umsMenu
     * @return
     */
    @PostMapping("create")
    public CommonResult create(@RequestBody UmsMenu umsMenu){
        umsMenuService.create(umsMenu);
        return CommonResult.success(1);
    }

    /**
     * 根据ID删除后台菜单
     * @param id
     * @return
     */
    @PostMapping("delete/{id}")
    public CommonResult delete(@PathVariable Integer id){
        umsMenuService.delete(id);
        return CommonResult.success(1);
    }


}
