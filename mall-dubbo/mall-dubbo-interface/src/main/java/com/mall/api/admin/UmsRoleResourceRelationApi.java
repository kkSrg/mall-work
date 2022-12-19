package com.mall.api.admin;

import java.util.List;

public interface UmsRoleResourceRelationApi {

    //根据角色id查资源
    List<Long> getIds(Long roleId);

    //给角色分配资源
    void update(Long roleId, Long[] resourceIds);

    //删除角色对应的资源
    void delete(Long[] ids);
}
