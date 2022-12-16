package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  秒杀活动表
 *
 * @author 吴一飞
 * @date 2022/12/16 15:30
 */


@Data
@TableName("sms_flash_promotion")
public class SmsFlashPromotion implements Serializable {


    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 活动名称
     */
    private String title;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    /**
     * 上下线状态 暂不清楚 0 1
     */
    private Integer status;


    /**
     * 秒杀时间段名称
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
