package com.mall.dubbo.api;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsAdminRoleRelationApi;
import com.mall.dubbo.mapper.UmsAdminRoleRelationMapper;
import com.mall.pojo.Admin;
import com.mall.pojo.UmsAdminRoleRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsAdminRoleRelationApiImpl implements UmsAdminRoleRelationApi {
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;


    //根据用户id查出所有角色id
    @Override
    public List<Long> getIds(Long adminId) {
        LambdaQueryWrapper<UmsAdminRoleRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(adminId != null, UmsAdminRoleRelation::getAdminId, adminId);
        List<UmsAdminRoleRelation> umsAdminRoleRelations = umsAdminRoleRelationMapper.selectList(lqw);
        return CollectionUtil.getFieldValues(umsAdminRoleRelations, "roleId", Long.class);
    }

    @Override
    public void update(Long adminId, Long[] roleIds) {
        //先删除以前的记录
        LambdaQueryWrapper<UmsAdminRoleRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(adminId != null, UmsAdminRoleRelation::getAdminId, adminId);
        umsAdminRoleRelationMapper.delete(lqw);
        //再把最新的数据保存
        for (Long roleId : roleIds) {
            UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
            umsAdminRoleRelation.setAdminId(adminId);
            umsAdminRoleRelation.setRoleId(roleId);
            umsAdminRoleRelationMapper.insert(umsAdminRoleRelation);
        }
    }

    @Override
    public void delete(Long adminId) {
        LambdaQueryWrapper<UmsAdminRoleRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(adminId != null, UmsAdminRoleRelation::getAdminId, adminId);
        umsAdminRoleRelationMapper.delete(lqw);
    }
}
