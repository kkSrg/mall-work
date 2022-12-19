package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("ums_resource_category")
@Data
public class UmsResourceCategory implements Serializable {

    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS")
    private Date createTime;//创建时间

    private String name;//分类名称
    private Integer sort;//排序

}
