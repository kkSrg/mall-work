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

    //根据菜单id查菜单
    List<UmsMenu> findByIds(List<Long> menuIds);

    //根据父级id查
    List<UmsMenu> getByParentId(Long parentId);
}
