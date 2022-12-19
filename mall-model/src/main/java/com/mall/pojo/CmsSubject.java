package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("cms_subject")
public class CmsSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String title;

    private String pic;

    private Integer productCount;

    private Integer recommendStatus;

    private LocalDateTime createTime;

    private Integer collectCount;

    private Integer readCount;

    private Integer commentCount;

    private String albumPics;

    private String description;

    private Integer showStatus;

    private String content;

    private Integer forwardCount;

    private String categoryName;

}
