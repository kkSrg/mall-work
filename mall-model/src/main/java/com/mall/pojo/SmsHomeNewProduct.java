package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 新鲜好物表
 *
 * @author 吴一飞
 * @date 2022/12/18 16:02
 */

@TableName("sms_home_new_product")
@Data
public class SmsHomeNewProduct implements Serializable {

    private Long id;
    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;
}
