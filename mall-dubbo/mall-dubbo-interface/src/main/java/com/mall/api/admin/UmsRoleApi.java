package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.UmsRole;

import java.util.List;

public interface UmsRoleApi {
    List<UmsRole> findAll();

    //根据角色id获取角色
    List<UmsRole> findByIds(List<Long> roleIds);

    //根据关键字获取分页数据
    IPage findAllByPage(String keyword, Integer page, Integer pagesize);

    //添加角色
    void save(UmsRole umsRole);

    //修改角色状态
    void updateStatus(Long roleId, Integer status);

    //修改角色信息
    void updateInfo(Long roleId, UmsRole umsRole);

    //删除
    void delete(Long[] ids);
}
