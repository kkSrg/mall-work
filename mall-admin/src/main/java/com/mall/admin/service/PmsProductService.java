package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.*;
import com.mall.dto.PmsProductDto;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.*;
import com.mall.vo.PmsProductVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsProductService {

    @DubboReference
    private PmsProductApi pmsProductApi;

    @DubboReference
    private PmsMemberPriceApi pmsMemberPriceApi;

    @DubboReference
    private CmsPrefrenceAreaProductRelationApi cmsPrefrenceAreaProductRelationApi;

    @DubboReference
    private PmsProductAttributeValueApi pmsProductAttributeValueApi;

    @DubboReference
    private PmsProductFullReductionApi pmsProductFullReductionApi;

    @DubboReference
    private PmsProductLadderApi pmsProductLadderApi;

    @DubboReference
    private PmsSkuStockApi pmsSkuStockApi;

    @DubboReference
    private CmsSubjectProductRelationApi cmsSubjectProductRelationApi;


    /**
     * 查询商品
     * @param pmsProductListDto
     * @param page
     * @param pagesize
     */
    public CommonPage<PmsProduct> getPage(PmsProductListDto pmsProductListDto, Integer page, Integer pagesize) {
        CommonPage<PmsProduct> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);
        IPage<PmsProduct> iPage = pmsProductApi.getList(pmsProductListDto, page, pagesize);
        listPage.setList(iPage.getRecords());
        listPage.setTotal(Math.toIntExact(iPage.getTotal()));
        listPage.setTotalPage(Math.toIntExact(iPage.getPages()));

        return listPage;
    }

    /**
     * 1.根据商品名称或货号模糊查询
     * @return
     */
    public List<PmsProduct> simpleList(String keyword) {
        List<PmsProduct> list = pmsProductApi.simpleList(keyword);
        return list;
    }

    /**
     * 2.根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    public PmsProductVo updateInfoById(Long id) {
        PmsProductVo vo = new PmsProductVo();
        PmsProduct pmsProduct = pmsProductApi.getMsgById(id);
        List<PmsMemberPrice> memberPriceList = pmsMemberPriceApi.getList(id);
        List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = cmsPrefrenceAreaProductRelationApi.getList(id);
        List<PmsProductAttributeValue> productAttributeValueList = pmsProductAttributeValueApi.getList(id);
        List<PmsProductFullReduction> productFullReductionList = pmsProductFullReductionApi.getList(id);
        List<PmsProductLadder> productLadderList = pmsProductLadderApi.getList(id);
        List<PmsSkuStock> skuStockList = pmsSkuStockApi.getList(id);
        List<CmsSubjectProductRelation> subjectProductRelationList = cmsSubjectProductRelationApi.getList(id);
        BeanUtil.copyProperties(pmsProduct,vo);
        vo.setMemberPriceList(memberPriceList);
        vo.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationList);
        vo.setProductAttributeValueList(productAttributeValueList);
        vo.setProductFullReductionList(productFullReductionList);
        vo.setProductLadderList(productLadderList);
        vo.setSkuStockList(skuStockList);
        vo.setSubjectProductRelationList(subjectProductRelationList);
        return vo;
    }

    /**
     * 3.创建商品
     * @param productParam
     * @return
     */
    public void create(PmsProductDto productParam) {
        PmsProduct pmsProduct = new PmsProductDto();
        BeanUtil.copyProperties(productParam,pmsProduct);
        pmsProductApi.insert(pmsProduct);
        pmsMemberPriceApi.insert(productParam.getMemberPriceList());
        cmsPrefrenceAreaProductRelationApi.insert(productParam.getPrefrenceAreaProductRelationList());
        pmsProductAttributeValueApi.insert(productParam.getProductAttributeValueList());
        pmsProductFullReductionApi.insert(productParam.getProductFullReductionList());
        pmsProductLadderApi.insert(productParam.getProductLadderList());
        pmsSkuStockApi.insert(productParam.getSkuStockList());
        cmsSubjectProductRelationApi.insert(productParam.getSubjectProductRelationList());
    }

    /**
     * 4.更新商品
     * @param id
     * @param productParam
     * @return
     */
    public void updateById(Long id, PmsProductDto productParam) {
        PmsProduct pmsProduct = new PmsProductDto();
        BeanUtil.copyProperties(productParam,pmsProduct,"id");
        pmsProductApi.update(id,pmsProduct);
        pmsMemberPriceApi.update(id,productParam.getMemberPriceList());
        cmsPrefrenceAreaProductRelationApi.update(id,productParam.getPrefrenceAreaProductRelationList());
        pmsProductAttributeValueApi.update(id,productParam.getProductAttributeValueList());
        pmsProductFullReductionApi.update(id,productParam.getProductFullReductionList());
        pmsProductLadderApi.update(id,productParam.getProductLadderList());
        pmsSkuStockApi.update(id,productParam.getSkuStockList());
        cmsSubjectProductRelationApi.update(id,productParam.getSubjectProductRelationList());
    }

    /**
     * 5.批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return
     */
    public void deleteStatus(List<Integer> ids, Integer deleteStatus) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductApi.deleteStatus(idList,deleteStatus);
    }

    /**
     * 6.批量设为新品
     * @param ids
     * @param newStatus
     * @return
     */
    public void newStatus(List<Integer> ids, Integer newStatus) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductApi.newStatus(idList,newStatus);
    }

    /**
     * 7.批量上下架商品
     * @param ids
     * @param publishStatus
     * @return
     */
    public void publishStatus(List<Integer> ids, Integer publishStatus) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductApi.publishStatus(idList,publishStatus);
    }

    /**
     * 8.批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return
     */
    public void recommendStatus(List<Integer> ids, Integer recommendStatus) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductApi.recommendStatus(idList,recommendStatus);
    }

    /**
     * 9.批量修改审核状态
     * @param detail
     * @param ids
     * @param verifyStatus
     * @return
     */
    public void verifyStatus(String detail, List<Integer> ids, Integer verifyStatus) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductApi.verifyStatus(detail,idList,verifyStatus);
    }
}
