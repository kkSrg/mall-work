package com.mall.api.admin;

import java.util.List;

public interface UmsAdminRoleRelationApi {
    //获取指定用户所有角色id
    List<Long> getIds(Long adminId);

    //给用户分配角色
    void update(Long adminId,Long[] roleIds);

    //删除用户和角色关系数据
    void delete(Long adminId);
}
