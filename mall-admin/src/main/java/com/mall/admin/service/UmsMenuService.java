package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.UmsMenuApi;
import com.mall.pojo.UmsMenu;
import com.mall.vo.UmsMenuVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UmsMenuService {

    @DubboReference
    private UmsMenuApi umsMenuApi;

    /**
     * 分页查询后台菜单
     *
     * @param parentId
     * @param page
     * @param pagesize
     */
    public CommonPage<UmsMenu> list(Integer parentId, Integer page, Integer pagesize) {
        CommonPage<UmsMenu> result = new CommonPage<>();
        result.setPageNum(page);
        result.setPageSize(pagesize);
        IPage<UmsMenu> iPage = umsMenuApi.getPageByParentId(parentId, page, pagesize);
        result.setTotal(Convert.toInt(iPage.getTotal()));
        result.setTotalPage(Convert.toInt(iPage.getPages()));
        result.setList(iPage.getRecords());
        return result;
    }

    /**
     * 修改菜单显示状态
     *
     * @param id
     * @param hidden
     */
    public void updateHidden(Integer id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setHidden(hidden);
        umsMenuApi.update(id, umsMenu);

    }

    /**
     * 根据ID获取菜单详情
     *
     * @param id
     */
    public UmsMenu findById(Integer id) {
        UmsMenu umsMenu = umsMenuApi.findById(id);
        return umsMenu;
    }

    /**
     * 修改后台菜单
     *
     * @param id
     * @param umsMenu
     */
    public void update(Integer id, UmsMenu umsMenu) {
        umsMenuApi.update(id, umsMenu);
    }

    /**
     * 添加后台菜单
     *
     * @param umsMenu
     */
    public void create(UmsMenu umsMenu) {
        umsMenuApi.create(umsMenu);
    }

    /**
     * 根据ID删除后台菜单
     *
     * @param id
     */
    public void delete(Integer id) {
        umsMenuApi.delete(id);
    }

    //树形结构返回所有菜单列表
    public List<UmsMenuVo> treeList() {
        //先查父级菜单列表parentId=0
        List<UmsMenu> umsMenus = umsMenuApi.getByParentId(0L);
        //遍历处理子级目录
        List<UmsMenuVo> voList = umsMenus.stream().map(umsMenu -> {
            UmsMenuVo vo = new UmsMenuVo();
            BeanUtils.copyProperties(umsMenu, vo);
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsMenu.getCreateTime()));
            List<UmsMenu> childrends = umsMenuApi.getByParentId(umsMenu.getId());
            //处理子级数据
            List<UmsMenuVo> vos = childrends.stream().map(childrend -> {
                UmsMenuVo vo2 = new UmsMenuVo();
                BeanUtils.copyProperties(childrend, vo2);
                vo2.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsMenu.getCreateTime()));
                vo2.setChildren(new ArrayList<>());
                return vo2;
            }).collect(Collectors.toList());
            vo.setChildren(vos);
            return vo;
        }).collect(Collectors.toList());

        return voList;
    }
}
