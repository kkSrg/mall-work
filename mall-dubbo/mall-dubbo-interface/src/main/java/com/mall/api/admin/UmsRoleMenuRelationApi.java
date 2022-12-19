package com.mall.api.admin;

import java.util.List;

public interface UmsRoleMenuRelationApi {
    //获取指定角色下所有菜单id
    List<Long> getIds(Long roleId);

    //给角色分配菜单
    void update(Long roleId, Long[] menuIds);

    //删除角色菜单关系表
    void delete(Long[] ids);
}
