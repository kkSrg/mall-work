package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class UmsRole implements Serializable {

    private Long id;
    private String name;//名称
    private String description;//描述
    private Integer adminCount;//后台用户数量

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//创建时间

    private Integer status;//启用状态: 0->禁用; 1->启用
    private Integer sort;//排序

}
