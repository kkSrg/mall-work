package com.mall.dubbo.api;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsRoleResourceRelationApi;
import com.mall.dubbo.mapper.UmsRoleResourceRelationMapper;
import com.mall.pojo.UmsRoleResourceRelation;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsRoleResourceRelationApiImpl implements UmsRoleResourceRelationApi {

    @Autowired
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

    @Override
    public List<Long> getIds(Long roleId) {
        LambdaQueryWrapper<UmsRoleResourceRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(roleId != null, UmsRoleResourceRelation::getRoleId, roleId);
        List<UmsRoleResourceRelation> umsRoleResourceRelations = umsRoleResourceRelationMapper.selectList(lqw);
        return CollectionUtil.getFieldValues(umsRoleResourceRelations, "resourceId", Long.class);
    }

    @Override
    public void update(Long roleId, Long[] resourceIds) {
        //先删除以前的记录
        LambdaQueryWrapper<UmsRoleResourceRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(roleId != null, UmsRoleResourceRelation::getRoleId, roleId);
        umsRoleResourceRelationMapper.delete(lqw);
        //再把最新的数据保存
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();
            umsRoleResourceRelation.setRoleId(roleId);
            umsRoleResourceRelation.setResourceId(resourceId);
            umsRoleResourceRelationMapper.insert(umsRoleResourceRelation);
        }
    }

    @Override
    public void delete(Long[] ids) {
        LambdaQueryWrapper<UmsRoleResourceRelation> lqw = new LambdaQueryWrapper<>();
        lqw.in(UmsRoleResourceRelation::getRoleId, ids);
        umsRoleResourceRelationMapper.delete(lqw);
    }
}
