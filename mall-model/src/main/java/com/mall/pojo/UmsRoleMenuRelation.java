package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UmsRoleMenuRelation implements Serializable {

    private Long id;
    //角色ID
    private Long roleId;
    //菜单ID
    private Long menuId;
}
