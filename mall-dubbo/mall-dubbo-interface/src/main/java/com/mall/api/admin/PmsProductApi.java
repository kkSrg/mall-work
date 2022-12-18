package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.dto.PmsProductListDto;
import com.mall.pojo.PmsProduct;

import java.util.List;

public interface PmsProductApi {
    IPage<PmsProduct> getList(PmsProductListDto pmsProductListDto, Integer page, Integer pagesize);
}
