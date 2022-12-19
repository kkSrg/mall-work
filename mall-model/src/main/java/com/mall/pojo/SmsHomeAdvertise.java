package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("sms_home_advertise")
public class SmsHomeAdvertise implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer type;

    private String pic;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer status;

    private Integer clickCount;

    private Integer orderCount;

    private String url;

    private String note;

    private Integer sort;
}
