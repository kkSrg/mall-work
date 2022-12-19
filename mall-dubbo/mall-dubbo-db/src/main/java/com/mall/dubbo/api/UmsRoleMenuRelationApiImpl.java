package com.mall.dubbo.api;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsRoleMenuRelationApi;
import com.mall.dubbo.mapper.UmsRoleMenuRelationMapper;
import com.mall.pojo.UmsRoleMenuRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsRoleMenuRelationApiImpl implements UmsRoleMenuRelationApi {

    @Autowired
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;


    @Override
    public List<Long> getIds(Long roleId) {
        LambdaQueryWrapper<UmsRoleMenuRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(roleId != null, UmsRoleMenuRelation::getRoleId, roleId);
        List<UmsRoleMenuRelation> umsRoleMenuRelations = umsRoleMenuRelationMapper.selectList(lqw);
        return CollectionUtil.getFieldValues(umsRoleMenuRelations, "menuId", Long.class);
    }

    @Override
    public void update(Long roleId, Long[] menuIds) {
        //先删除以前的记录
        LambdaQueryWrapper<UmsRoleMenuRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(roleId != null, UmsRoleMenuRelation::getRoleId, roleId);
        umsRoleMenuRelationMapper.delete(lqw);
        //再把最新的数据保存
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation umsRoleMenuRelation = new UmsRoleMenuRelation();
            umsRoleMenuRelation.setRoleId(roleId);
            umsRoleMenuRelation.setMenuId(menuId);
            umsRoleMenuRelationMapper.insert(umsRoleMenuRelation);
        }
    }

    @Override
    public void delete(Long[] ids) {
        LambdaQueryWrapper<UmsRoleMenuRelation> lqw = new LambdaQueryWrapper<>();
        lqw.in(UmsRoleMenuRelation::getRoleId, ids);
        umsRoleMenuRelationMapper.delete(lqw);
    }
}
