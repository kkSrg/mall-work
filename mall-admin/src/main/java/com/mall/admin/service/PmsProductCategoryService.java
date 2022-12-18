package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import com.mall.api.admin.PmsProductCategoryApi;
import com.mall.pojo.PmsProductCategory;
import com.mall.vo.PmsProductCategoryWithChildrenItem;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsProductCategoryService {

    @DubboReference
    private PmsProductCategoryApi pmsProductCategoryApi;

    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        List<PmsProductCategory> pmsProductCategorieParent = pmsProductCategoryApi.listWithParent();

        List<PmsProductCategoryWithChildrenItem> list = pmsProductCategorieParent.stream().map(pmsProductCategory -> {
            PmsProductCategoryWithChildrenItem vo = new PmsProductCategoryWithChildrenItem();
            BeanUtil.copyProperties(pmsProductCategory, vo);
            long id = pmsProductCategory.getId();
            List<PmsProductCategory> pmsProductCategorieChildren = pmsProductCategoryApi.listWithChildren(id);
            vo.setChildren(pmsProductCategorieChildren);
            return vo;
        }).collect(Collectors.toList());
        return list;
    }
}
