package com.mall.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.pojo.UmsMenu;

import java.util.List;

public interface UmsMenuApi {
    List<UmsMenu> findAll();

    IPage<UmsMenu> getPageByParentId(Integer parentId, Integer page, Integer pagesize);

    void update(Integer id, UmsMenu umsMenu);

    UmsMenu findById(Integer id);

    void create(UmsMenu umsMenu);

    void delete(Integer id);
}
