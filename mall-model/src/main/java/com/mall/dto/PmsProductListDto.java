package com.mall.dto;

import lombok.Data;

@Data
public class PmsProductListDto {

    private Integer brandId;//商品品牌编号
    private String keyword;//商品名称模糊关键字
    private Integer productCategoryId;//商品分类编号
    private String productSn;//商品货号
    private Integer publishStatus;//上架状态
    private Integer verifyStatus;//审核状态
}
