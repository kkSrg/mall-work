package com.mall.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * 首页推荐品牌表
 *
 * @author 吴一飞
 * @date 2022/12/17 19:37
 */
@TableName("sms_home_brand")
@Data
public class SmsHomeBrand implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    private Long brandId;
    private String brandName;
    private Integer recommendStatus;
    private Integer sort;


}
