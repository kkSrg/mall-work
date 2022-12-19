package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsRoleService;
import com.mall.pojo.Admin;
import com.mall.pojo.UmsRole;
import com.mall.vo.AdminVo;
import com.mall.vo.UmsMenuVo;
import com.mall.vo.UmsResourceVo;
import com.mall.vo.UmsRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("listAll")
    public CommonResult<List<UmsRoleVo>> listAll() {
        List<UmsRoleVo> result = umsRoleService.listAll();
        return CommonResult.success(result);
    }

    /**
     * 根据角色名称分页获取角色列表
     *
     * @return
     */
    @GetMapping("list")
    public CommonResult<CommonPage<UmsRoleVo>> list(String keyword,
                                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pagesize) {
        CommonPage<UmsRoleVo> result = umsRoleService.adminsByKw(keyword, page, pagesize);
        return CommonResult.success(result);
    }

    /**
     * 添加角色
     *
     * @param
     * @return
     */
    @PostMapping("create")
    public CommonResult create(@RequestBody UmsRole umsRole) {
        umsRoleService.create(umsRole);
        return CommonResult.success("操作成功");
    }

    /**
     * 修改角色状态
     *
     * @param roleId
     * @param status
     * @return
     */
    @PostMapping("/updateStatus/{roleId}")
    public CommonResult updateStatus(@PathVariable Long roleId, Integer status) {
        umsRoleService.updateStatus(roleId, status);
        return CommonResult.success("操作成功");
    }

    /**
     * 修改指定角色信息
     *
     * @param roleId
     * @param umsRoleVo
     * @return
     */
    @PostMapping("/update/{roleId}")
    public CommonResult update(@PathVariable Long roleId, @RequestBody UmsRoleVo umsRoleVo) {
        umsRoleService.updateInfo(roleId, umsRoleVo);
        return CommonResult.success("操作成功");
    }

    /**
     * 获取角色相关菜单
     *
     * @param roleId
     * @return
     */
    @GetMapping("/listMenu/{roleId}")
    public CommonResult listMenu(@PathVariable Long roleId) {
        List<UmsMenuVo> voList = umsRoleService.listMenu(roleId);
        return CommonResult.success(voList);
    }

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(Long roleId, @RequestParam Long[] menuIds) {
        umsRoleService.update(roleId, menuIds);
        return CommonResult.success("操作成功");
    }

    /**
     * 获取角色相关资源
     *
     * @param roleId
     * @return
     */
    @GetMapping("/listResource/{roleId}")
    public CommonResult listResource(@PathVariable Long roleId) {
        List<UmsResourceVo> voList = umsRoleService.listResource(roleId);
        return CommonResult.success(voList);
    }
}
