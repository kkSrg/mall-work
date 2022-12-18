package com.mall.vo;

import com.mall.pojo.PmsProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    List<PmsProductCategory> children;
}
