package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 场次表
 *
 * @author 吴一飞
 * @date 2022/12/16 21:49
 */
@Data
@TableName("sms_flash_promotion_session")
public class SmsFlashPromotionSession implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 场次名称
     */
    private String name;

    /**
     * 每日开始时间
     */
    private Date startTime;

    /**
     * 每日结束时间
     */
    private Date endTime;

    /**
     * 启用状态：0->不启用；1->启用
     */
    private Integer status;


    /**
     * 创建时间
     */
    private Date createTime;
}
