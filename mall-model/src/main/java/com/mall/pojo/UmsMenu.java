package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UmsMenu implements Serializable {
    private Long id;//菜单id
    private  Long parentId;//父级id
    private Date createTime;//创建时间
    private String title;//菜单名称
    private Integer level;//菜单级数
    private Integer sort;//菜单排序
    private String name;//前端名称
    private String icon;//前端图标
    private Integer hidden;//前端隐藏
}
