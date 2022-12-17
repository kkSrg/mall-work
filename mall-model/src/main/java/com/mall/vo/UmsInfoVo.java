package com.mall.vo;

import com.mall.pojo.UmsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UmsInfoVo implements Serializable {

    private String[] roles;//角色
    private String icon;//头像
    private List<UmsMenu> menus;//菜单
    private String username;//用户名字
}
