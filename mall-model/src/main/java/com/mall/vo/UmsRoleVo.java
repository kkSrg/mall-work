package com.mall.vo;


import lombok.Data;

import java.io.Serializable;


@Data
public class UmsRoleVo implements Serializable {

    private Long id;
    private String name;//名称
    private String description;//描述
    private Integer adminCount;//后台用户数量
    private String createTime;//创建时间
    private Integer status;//启用状态: 0->禁用; 1->启用
    private Integer sort;//排序

}
