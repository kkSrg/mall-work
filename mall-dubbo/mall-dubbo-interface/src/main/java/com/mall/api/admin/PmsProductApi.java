package com.mall.api.admin;

import com.mall.dto.PmsProductListDto;
import com.mall.pojo.PmsProduct;

import java.util.List;

public interface PmsProductApi {
    List<PmsProduct> getList(PmsProductListDto pmsProductListDto, Integer page, Integer pagesize);
}
