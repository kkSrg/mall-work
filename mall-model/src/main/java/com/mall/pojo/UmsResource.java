package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UmsResource implements Serializable {
    private Long id;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS")
    private Date createTime;
    //资源名称
    private String name;
    //资源URL
    private String url;
    //描述
    private String description;
    //资源分类ID
    private Long categoryId;
}
