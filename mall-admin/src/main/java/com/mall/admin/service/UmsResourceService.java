package com.mall.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.UmsResourceApi;
import com.mall.pojo.PmsProduct;
import com.mall.pojo.UmsResource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UmsResourceService {


    @DubboReference
    private UmsResourceApi umsResourceApi;

    /**
     * 分页模糊查询后台资源
     * @param page
     * @param pagesize
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     */
    public CommonPage<UmsResource> getPage(Integer page, Integer pagesize, Integer categoryId, String nameKeyword, String urlKeyword) {
        CommonPage<UmsResource> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);
        IPage<UmsResource> iPage = umsResourceApi.getList(categoryId,nameKeyword,urlKeyword, page, pagesize);
        listPage.setList(iPage.getRecords());
        listPage.setTotal(Math.toIntExact(iPage.getTotal()));
        listPage.setTotalPage(Math.toIntExact(iPage.getPages()));
        return listPage;
    }
}
