package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UmsMenu implements Serializable {
    private Long id;//菜单id
    private  Long parentId;//父级id

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS")
    private Date createTime;//创建时间

    private String title;//菜单名称
    private Integer level;//菜单级数
    private Integer sort;//菜单排序
    private String name;//前端名称
    private String icon;//前端图标
    private Integer hidden;//前端隐藏
}
