package com.mall.dto;

import lombok.Data;

/**
 * 接收用户名密码实体类
 *
 * @author 吴一飞
 * @date 2022/12/15 21:58
 */

@Data
public class UmsAdminLoginParam {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
