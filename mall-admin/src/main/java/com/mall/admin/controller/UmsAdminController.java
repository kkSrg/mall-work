package com.mall.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.UmsAdminService;
import com.mall.dto.UmsAdminLoginParam;
import com.mall.pojo.Admin;
import com.mall.vo.AdminVo;
import com.mall.vo.UmsInfoVo;
import com.mall.vo.UmsRoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    /**
     * 登录功能
     *
     * @param umsAdminLoginParam
     * @return
     */
    @PostMapping("/login")
    public CommonResult<Object> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        //获取用户密码
        String username = umsAdminLoginParam.getUsername();
        String password = umsAdminLoginParam.getPassword();

        //请求service方法
        String token = umsAdminService.login(username, password);

        Map<String,String> map = new HashMap<>();
        map.put("tokenHead","Bearer ");
        map.put("token",token);

      return CommonResult.success(map);
    }


    @GetMapping("/info")
    public CommonResult<UmsInfoVo> info() {
        UmsInfoVo vo = umsAdminService.getInfo();
        return CommonResult.success(vo);

    }


    /**
     * 根据用户名或姓名分页获取用户列表
     *
     * @param keyword
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("list")
    public CommonResult<CommonPage<AdminVo>> list(String keyword,
                                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pagesize) {
        CommonPage<AdminVo> result = umsAdminService.adminsByKw(keyword, page, pagesize);
        return CommonResult.success(result);
    }

    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @PostMapping("register")
    public CommonResult<AdminVo> register(@RequestBody Admin admin) {
        AdminVo adminVo = umsAdminService.register(admin);
        return CommonResult.success(adminVo);
    }


    /**
     * 3.向外提供一个token校验接口
     */
    @GetMapping("/{token}")
    public Long parseToken(@PathVariable("token") String token) {
        String[] s = StrUtil.split(token, " ");
        if (s.length == 2) {
            token = s[1];
        }
        return umsAdminService.getToken(token);
    }

    /**
     * 获取指定用户的角色
     *
     * @param adminId
     * @return
     */
    @GetMapping("/role/{adminId}")
    public CommonResult getRoleById(@PathVariable Long adminId) {
        List<UmsRoleVo> result = umsAdminService.getRoleById(adminId);
        return CommonResult.success(result);
    }

    /**
     * 修改帐号状态
     *
     * @param adminId
     * @param status
     * @return
     */
    @PostMapping("/updateStatus/{adminId}")
    public CommonResult updateStatus(@PathVariable Long adminId, Integer status) {
        umsAdminService.updateStatus(adminId, status);
        return CommonResult.success("操作成功");
    }

    /**
     * 给用户分配角色
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    @PostMapping("/role/update")
    public CommonResult update(Long adminId, @RequestParam Long[] roleIds) {
        log.info("roleIds===" + roleIds);
        umsAdminService.update(adminId, roleIds);
        return CommonResult.success("操作成功");
    }

    /**
     * 修改指定用户信息
     *
     * @param adminId
     * @param adminVo
     * @return
     */
    @PostMapping("/update/{adminId}")
    public CommonResult update(@PathVariable Long adminId, @RequestBody AdminVo adminVo) {
        umsAdminService.updateInfo(adminId, adminVo);
        return CommonResult.success("操作成功");
    }

    /**
     * 删除指定用户信息
     *
     * @param adminId
     * @return
     */
    @PostMapping("/delete/{adminId}")
    public CommonResult delete(@PathVariable Long adminId) {
        umsAdminService.delete(adminId);
        return CommonResult.success("操作成功");
    }

}
