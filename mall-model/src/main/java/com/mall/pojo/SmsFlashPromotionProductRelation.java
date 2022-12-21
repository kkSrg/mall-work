package com.mall.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品限时购与商品关系表(SmsFlashPromotionProductRelation)实体类
 *
 * @author makejava
 * @since 2022-12-21 10:03:22
 */
@Data
public class SmsFlashPromotionProductRelation implements Serializable {
    private static final long serialVersionUID = 363104350774631045L;
    /**
     * 编号
     */
    private Long id;
    
    private Long flashPromotionId;
    /**
     * 编号
     */
    private Long flashPromotionSessionId;
    
    private Long productId;
    /**
     * 限时购价格
     */
    private Double flashPromotionPrice;
    /**
     * 限时购数量
     */
    private Integer flashPromotionCount;
    /**
     * 每人限购数量
     */
    private Integer flashPromotionLimit;
    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private PmsProduct product;

}

