package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.PmsProductApi;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.Admin;
import com.mall.pojo.PmsProduct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsProductService {

    @DubboReference
    private PmsProductApi pmsProductApi;


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
}
