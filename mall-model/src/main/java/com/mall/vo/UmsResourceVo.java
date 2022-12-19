package com.mall.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UmsResourceVo implements Serializable {

    private Long id;
    //创建时间
    private String createTime;
    //资源名称
    private String name;
    //资源URL
    private String url;
    //描述
    private String description;
    //资源分类ID
    private Long categoryId;
}

