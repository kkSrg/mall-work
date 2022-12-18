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
        List<PmsProduct> list = pmsProductApi.getList(pmsProductListDto,page,pagesize);
        listPage.setList(list);
        listPage.setTotal(list.size());
        listPage.setTotalPage((int) (list.size() / pagesize + ((list.size() % pagesize == 0) ? 0 : 1)));

        return listPage;
    }
}
