package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
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


    /**
     * 1.分页查询商品分类
     * @return
     */
    public CommonPage<PmsProductCategory> list(Integer parentId, Integer pageNum, Integer pageSize) {
        CommonPage<PmsProductCategory> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        List<PmsProductCategory> list = pmsProductCategoryApi.findByParentId(parentId);
        result.setList(list);
        result.setTotal(list.size());
        result.setTotalPage((int) (list.size() / pageSize + ((list.size() % pageSize == 0) ? 0 : 1)));
        return result;
    }


    /**
     * 查询所有一级分类及子分类
     * @return
     */
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

    /**
     * 2.根据id获取商品分类
     * @return
     */
    public PmsProductCategory getMsgById(Integer id) {
        PmsProductCategory category = pmsProductCategoryApi.getMsgById(Convert.toLong(id));
        return category;
    }

    /**
     * 3.添加商品分类
     * @return
     */
    public void create(PmsProductCategory category) {
        pmsProductCategoryApi.create(category);
    }

    /**
     * 4.删除商品分类
     * @param id
     * @return
     */
    public void deleteById(Integer id) {
        pmsProductCategoryApi.deleteById(Convert.toLong(id));
    }

    /**
     * 5.修改商品分类
     * @param id
     * @param category
     * @return
     */
    public void updateById(Integer id, PmsProductCategory category) {
        pmsProductCategoryApi.updateById(Convert.toLong(id),category);
    }

    /**
     * 6.修改导航栏显示状态
     * @param ids
     * @param navStatus
     * @return
     */
    public void updateNavStatus(List<Integer> ids, Integer navStatus) {
        List<Long> idList = ids.stream().map(id->{
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductCategoryApi.updateNavStatus(idList,navStatus);
    }

    /**
     * 7.修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    public void updateShowStatus(List<Integer> ids, Integer showStatus) {
        List<Long> idList = ids.stream().map(id->{
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsProductCategoryApi.updateShowStatus(idList,showStatus);
    }
}
