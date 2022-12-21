package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
import com.mall.api.admin.UmsAdminApi;
import com.mall.api.admin.UmsAdminRoleRelationApi;
import com.mall.api.admin.UmsMenuApi;
import com.mall.api.admin.UmsRoleApi;
import com.mall.dto.UmsAdminParam;
import com.mall.exception.ConsumerException;
import com.mall.pojo.Admin;
import com.mall.pojo.UmsAdminRoleRelation;
import com.mall.pojo.UmsMenu;
import com.mall.pojo.UmsRole;
import com.mall.utils.AppJwtUtil;
import com.mall.utils.ThreadLocalUtil;
import com.mall.vo.AdminVo;
import com.mall.vo.UmsInfoVo;
import com.mall.vo.UmsRoleVo;
import io.jsonwebtoken.Claims;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsAdminService {

    @DubboReference
    private UmsAdminApi umsAdminApi;

    @DubboReference
    private UmsRoleApi umsRoleApi;

    @DubboReference
    private UmsMenuApi umsMenuApi;

    @DubboReference
    private UmsAdminRoleRelationApi umsAdminRoleRelationApi;

    /**
     * 登录功能
     */
    public String login(String username, String password) {

        //将密码加密
        //  String encryptedPwd = MyMD5Util.getEncryptedPwd(password);
        String encryptedPwd = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库
        Admin admin = umsAdminApi.login(username, encryptedPwd);

        if (admin == null) {
            //用户名密码错误!!
            throw new ConsumerException("验证输入错误！");
        } else {
            //生成token
            String token = AppJwtUtil.getToken(admin.getId());
            return token;
        }
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     *
     * @param keyword
     * @param page
     * @param pagesize
     */
    public CommonPage<AdminVo> adminsByKw(String keyword, Integer page, Integer pagesize) {
        CommonPage<AdminVo> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);

        List<Admin> list = umsAdminApi.findAll(keyword, page, pagesize);
        List<AdminVo> adminVoList = list.stream().map(admin -> {
            AdminVo vo = new AdminVo();
            BeanUtil.copyProperties(admin, vo);
            vo.setCreateTime(admin.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()) : null);
            vo.setLoginTime(admin.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getLoginTime()) : null);
            return vo;
        }).collect(Collectors.toList());
        listPage.setList(adminVoList);
        listPage.setTotal(adminVoList.size());
        listPage.setTotalPage((int) (adminVoList.size() / pagesize + ((adminVoList.size() % pagesize == 0) ? 0 : 1)));
        return listPage;
    }

    /**
     * 用户注册
     *
     * @param
     */
    public AdminVo register(Admin admin) {
        Admin save = umsAdminApi.save(admin);
        AdminVo vo = new AdminVo();
        BeanUtil.copyProperties(save, vo);
        vo.setCreateTime(admin.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()) : null);
        vo.setLoginTime(admin.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getLoginTime()) : null);
        return vo;
    }


    //解析token
    public Long getToken(String token) {
        Claims claimsBody = AppJwtUtil.getClaimsBody(token);
        int flag = AppJwtUtil.verifyToken(claimsBody);
        //判断token是否在有效期
        if (flag == 1 || flag == 2) {
            throw new ConsumerException("token已失效");
        }
        return Convert.toLong(claimsBody.get("id"));
    }

    //获取当前登录者信息
    public UmsInfoVo getInfo() {
        //创建vo作为返回前端的容器
        UmsInfoVo vo = new UmsInfoVo();
        //根据token携带的用户id查看username
        Long id = ThreadLocalUtil.getId();
        //查ums_admin表
        Admin admin = umsAdminApi.findById(id);
        vo.setIcon(admin.getIcon());
        vo.setUsername(admin.getUsername());
        List<UmsRole> umsRoles = umsRoleApi.findAll();
        List<String> roles = CollectionUtil.getFieldValues(umsRoles, "name", String.class);
        //list转数组
        vo.setRoles(roles.toArray(new String[]{}));
        List<UmsMenu> umsMenus = umsMenuApi.findAll();
        //排序
        umsMenus.sort(Comparator.comparing(UmsMenu::getCreateTime));
        vo.setMenus(umsMenus);
        return vo;
    }

    //获取指定用户的角色
    public List<UmsRoleVo> getRoleById(Long adminId) {
        //获取指定用户所有角色id
        List<Long> roleIds = umsAdminRoleRelationApi.getIds(adminId);
        //没有对应角色id则返回空集合
        if (CollUtil.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        //根据角色id查角色
        List<UmsRole> roles = umsRoleApi.findByIds(roleIds);
        List<UmsRoleVo> voList = roles.stream().map(umsRole -> {
            UmsRoleVo vo = new UmsRoleVo();
            BeanUtil.copyProperties(umsRole, vo);
            //转为UTC时间格式
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsRole.getCreateTime()));
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    //修改帐号状态
    public void updateStatus(Long adminId, Integer status) {
        umsAdminApi.updateStatus(adminId, status);
    }

    //给用户分配角色
    public void update(Long adminId, Long[] roleIds) {
        umsAdminRoleRelationApi.update(adminId, roleIds);
    }

    //修改指定用户信息
    public void updateInfo(Long adminId, AdminVo adminVo) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminVo, admin);
        try {
            admin.setCreateTime(adminVo.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(adminVo.getCreateTime()) : null);
            admin.setLoginTime(adminVo.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(adminVo.getLoginTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        umsAdminApi.updateInfo(adminId, admin);
    }

    //删除指定用户信息
    public void delete(Long adminId) {
        //先删除admin表
        umsAdminApi.delete(adminId);
        //再删admin和role关系表
        umsAdminRoleRelationApi.delete(adminId);
    }

    //登出功能
    public void logout() {
        ThreadLocalUtil.close();
    }
}
