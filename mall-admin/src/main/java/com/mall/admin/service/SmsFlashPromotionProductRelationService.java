package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.PmsProductApi;
import com.mall.api.admin.SmsFlashPromotionProductRelationApi;
import com.mall.pojo.PmsProduct;
import com.mall.pojo.SmsFlashPromotionProductRelation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsFlashPromotionProductRelationService {


    @DubboReference
    private SmsFlashPromotionProductRelationApi smsFlashPromotionProductRelationApi;

    @DubboReference
    private PmsProductApi pmsProductApi;


    public CommonPage<SmsFlashPromotionProductRelation> getPage(Integer pageNum, Integer pageSize, Integer flashPromotionId, Integer flashPromotionSessionId) {

        IPage<SmsFlashPromotionProductRelation> page = new Page<>(pageNum,pageSize);

        IPage<SmsFlashPromotionProductRelation> iPage = smsFlashPromotionProductRelationApi.getPage(page,flashPromotionId,flashPromotionSessionId);
        List<SmsFlashPromotionProductRelation> records = iPage.getRecords();
        for (SmsFlashPromotionProductRelation smsFlashPromotionProductRelation : records) {
            PmsProduct pmsProduct = pmsProductApi.selectById(smsFlashPromotionProductRelation.getProductId());
            smsFlashPromotionProductRelation.setProduct(pmsProduct);
        }

        CommonPage<SmsFlashPromotionProductRelation> commonPage = new CommonPage<>();
        commonPage.setPageNum(pageNum);
        commonPage.setPageSize(pageSize);
        commonPage.setTotal(Math.toIntExact(iPage.getTotal()));
        commonPage.setTotalPage(Math.toIntExact(iPage.getPages()));
        commonPage.setList(records);

        return commonPage;
    }

    public void create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation smsFlashPromotionProductRelation : relationList) {
            smsFlashPromotionProductRelationApi.save(smsFlashPromotionProductRelation);
        }

    }

    public void deleteById(Long id) {
        smsFlashPromotionProductRelationApi.deleteById(id);
    }

    public void update(SmsFlashPromotionProductRelation smsFlashPromotionProductRelation) {
        smsFlashPromotionProductRelationApi.update(smsFlashPromotionProductRelation);

    }

    public SmsFlashPromotionProductRelation selectById(Long id) {
        return smsFlashPromotionProductRelationApi.selectById(id);
    }
}
