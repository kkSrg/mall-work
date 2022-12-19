package com.mall.vo;

import com.mall.pojo.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsProductVo extends PmsProduct implements Serializable {

    private List<PmsMemberPrice> memberPriceList;  //商品会员价格设置
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList; //优选专区和商品的关系
    private List<PmsProductAttributeValue> productAttributeValueList; //商品参数及自定义规格属性
    private List<PmsProductFullReduction> productFullReductionList; //商品满减价格设置
    private List<PmsProductLadder> productLadderList; //商品阶梯价格设置
    private List<PmsSkuStock> skuStockList; //商品的sku库存信息
    private List<CmsSubjectProductRelation> subjectProductRelationList; //专题和商品关系
}
