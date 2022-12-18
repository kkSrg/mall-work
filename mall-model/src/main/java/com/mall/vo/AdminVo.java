package com.mall.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;


@Data
public class AdminVo implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private String createTime;
    private String loginTime;
    private Integer status;

}
