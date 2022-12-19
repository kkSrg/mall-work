package com.mall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PmsProduct implements Serializable {
    private Long id;
    private Long brandId; //品牌id
    private Long productCategoryId;  //商品分类id
    private Long feightTemplateId;
    private Long productAttributeCategoryId;
    private String name;
    private String pic;
    private String productSn; //货号
    private Integer deleteStatus; //删除状态：0->未删除；1->已删除
    private Integer publishStatus; //上架状态：0->下架；1->上架
    private Integer newStatus; //新品状态:0->不是新品；1->新品
    private Integer recommandStatus; //推荐状态；0->不推荐；1->推荐
    private Integer verifyStatus; //审核状态：0->未审核；1->审核通过
    private Integer sort; //排序
    private Integer sale; //销量
    private Double price;
    private Double promotionPrice; //促销价格
    private Integer giftGrowth; //赠送的成长值
    private Integer giftPoint; //赠送的积分
    private Integer usePointLimit; //限制使用的积分数
    private String subTitle; //副标题
    private String description; //商品描述
    private Double originalPrice; //市场价
    private Integer stock; //库存
    private Integer lowStock; //库存预警值
    private String unit; //单位
    private Double weight; //商品重量，默认为克
    private Integer previewStatus; //是否为预告商品：0->不是；1->是
    private String serviceIds; //以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
    private String keywords;
    private String note;
    private String albumPics; //画册图片，连产品图片限制为5张，以逗号分割
    private String detailTitle;
    private String detailDesc;
    private String detailHtml; //产品详情网页内容
    private String detailMobileHtml; //移动端网页详情
    private Date promotionStartTime; //促销开始时间
    private Date promotionEndTime; //促销结束时间
    private Integer promotionPerLimit; //活动限购数量
    private Integer promotionType; //促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
    private String brandName; //品牌名称
    private String productCategoryName; //商品分类名称
}
