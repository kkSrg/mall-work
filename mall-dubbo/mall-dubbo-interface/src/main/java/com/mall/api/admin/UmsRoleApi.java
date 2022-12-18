package com.mall.api.admin;

import com.mall.pojo.UmsRole;

import java.util.List;

public interface UmsRoleApi {
    List<UmsRole> findAll();

    //根据用户id查所有角色
    List<UmsRole> findByIds(List<Long> roleIds);
}
