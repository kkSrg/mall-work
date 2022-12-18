package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UmsAdminRoleRelation implements Serializable {
    //umsadmin和umsrole关系表
    private Long id;
    private Long adminId;
    private Long roleId;
}
