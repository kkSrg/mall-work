package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.UmsRoleApi;
import com.mall.pojo.Admin;
import com.mall.pojo.UmsRole;
import com.mall.vo.AdminVo;
import com.mall.vo.UmsRoleVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleService {

    @DubboReference
    private UmsRoleApi umsRoleApi;

    /**
     * 获取所有角色
     *
     * @return
     */
    public List<UmsRoleVo> listAll() {
        List<UmsRole> listAll = umsRoleApi.findAll();
        //该用户下没有角色休息,返回空集合,前端才不会报错
        if (CollUtil.isEmpty(listAll)) {
            return new ArrayList<>();
        }
        List<UmsRoleVo> voList = listAll.stream().map(umsRole -> {
            UmsRoleVo vo = new UmsRoleVo();
            BeanUtil.copyProperties(umsRole, vo);
            //转为UTC时间格式
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsRole.getCreateTime()));
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    //根据角色名称分页获取角色列表
    public CommonPage<UmsRoleVo> adminsByKw(String keyword, Integer page, Integer pagesize) {
        CommonPage<UmsRoleVo> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);
        IPage resultPage = umsRoleApi.findAllByPage(keyword, page, pagesize);
        List<UmsRole> list = resultPage.getRecords();
        List<UmsRoleVo> umsRoleVos = list.stream().map(umsRole -> {
            UmsRoleVo vo = new UmsRoleVo();
            BeanUtil.copyProperties(umsRole, vo);
            //转为UTC时间格式
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsRole.getCreateTime()));
            return vo;
        }).collect(Collectors.toList());
        listPage.setList(umsRoleVos);
        listPage.setTotal(Convert.toInt(resultPage.getTotal()));
        listPage.setTotalPage(Convert.toInt(resultPage.getPages()));
        return listPage;
    }

    //添加角色
    public void create(UmsRole umsRole) {
        umsRole.setCreateTime(new Date());
        umsRoleApi.save(umsRole);
    }

    //修改角色状态
    public void updateStatus(Long roleId, Integer status) {
        umsRoleApi.updateStatus(roleId, status);
    }

    //修改指定角色信息
    public void updateInfo(Long roleId, UmsRoleVo umsRoleVo) {
        UmsRole umsRole = new UmsRole();
        BeanUtils.copyProperties(umsRoleVo, umsRole);
        try {
            umsRole.setCreateTime(umsRoleVo.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(umsRoleVo.getCreateTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        umsRoleApi.updateInfo(roleId, umsRole);
    }



}
