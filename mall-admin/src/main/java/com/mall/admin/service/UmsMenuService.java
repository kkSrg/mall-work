package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.UmsMenuApi;
import com.mall.pojo.UmsMenu;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UmsMenuService {

    @DubboReference
    private UmsMenuApi umsMenuApi;

    /**
     * 分页查询后台菜单
     * @param parentId
     * @param page
     * @param pagesize
     */
    public CommonPage<UmsMenu> list(Integer parentId, Integer page, Integer pagesize) {
        CommonPage<UmsMenu> result = new CommonPage<>();
        result.setPageNum(page);
        result.setPageSize(pagesize);
        IPage<UmsMenu> iPage = umsMenuApi.getPageByParentId(parentId,page,pagesize);
        result.setTotal(Convert.toInt(iPage.getTotal()));
        result.setTotalPage(Convert.toInt(iPage.getPages()));
        result.setList(iPage.getRecords());
        return result;
    }

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     */
    public void updateHidden(Integer id,Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setHidden(hidden);
        umsMenuApi.update(id,umsMenu);

    }

    /**
     * 根据ID获取菜单详情
     * @param id
     */
    public UmsMenu findById(Integer id) {
        UmsMenu umsMenu = umsMenuApi.findById(id);
        return umsMenu;
    }

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     */
    public void update(Integer id, UmsMenu umsMenu) {
        umsMenuApi.update(id,umsMenu);
    }

    /**
     * 添加后台菜单
     * @param umsMenu
     */
    public void create(UmsMenu umsMenu) {
        umsMenuApi.create(umsMenu);
    }

    /**
     * 根据ID删除后台菜单
     * @param id
     */
    public void delete(Integer id) {
        umsMenuApi.delete(id);
    }
}
