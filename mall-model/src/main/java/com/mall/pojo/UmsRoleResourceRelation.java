package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UmsRoleResourceRelation implements Serializable {

    private Long id;
    private Long roleId;
    private Long resourceId;

}
