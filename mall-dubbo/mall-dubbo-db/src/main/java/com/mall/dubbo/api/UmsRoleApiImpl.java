package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.UmsRoleApi;
import com.mall.dubbo.mapper.UmsRoleMapper;
import com.mall.pojo.Admin;
import com.mall.pojo.UmsRole;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsRoleApiImpl implements UmsRoleApi {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsRole> findAll() {
        return umsRoleMapper.selectList(null);
    }

    @Override
    public List<UmsRole> findByIds(List<Long> roleIds) {
        LambdaQueryWrapper<UmsRole> lqw = new LambdaQueryWrapper<>();
        lqw.in(UmsRole::getId, roleIds);
        return umsRoleMapper.selectList(lqw);
    }

    @Override
    public IPage findAllByPage(String keyword, Integer page, Integer pagesize) {
        IPage<UmsRole> pg = new Page<>(page, pagesize);
        LambdaQueryWrapper<UmsRole> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            wrapper.like(UmsRole::getName, keyword).or().like(UmsRole::getDescription, keyword);
        }
        umsRoleMapper.selectPage(pg, wrapper);
        return pg;
    }

    @Override
    public void save(UmsRole umsRole) {
        umsRoleMapper.insert(umsRole);
    }

    @Override
    public void updateStatus(Long roleId, Integer status) {
        LambdaUpdateWrapper<UmsRole> luw = new LambdaUpdateWrapper();
        luw.eq(UmsRole::getId, roleId).set(UmsRole::getStatus, status);
        umsRoleMapper.update(null, luw);
    }

    @Override
    public void updateInfo(Long roleId, UmsRole umsRole) {
        umsRoleMapper.updateById(umsRole);
    }

    @Override
    public void delete(Long[] ids) {
        LambdaQueryWrapper<UmsRole> lqw=new LambdaQueryWrapper<>();
        lqw.in(UmsRole::getId,ids);
        umsRoleMapper.delete(lqw);
    }
}
