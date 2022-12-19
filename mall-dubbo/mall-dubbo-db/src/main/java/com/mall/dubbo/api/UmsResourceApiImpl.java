package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.UmsResourceApi;
import com.mall.dubbo.mapper.UmsResourceMapper;
import com.mall.pojo.PmsProduct;
import com.mall.pojo.UmsMenu;
import com.mall.pojo.UmsResource;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@DubboService
public class UmsResourceApiImpl implements UmsResourceApi {

    @Autowired
    private UmsResourceMapper umsResourceMapper;


    @Override
    public IPage<UmsResource> getList(Integer categoryId, String nameKeyword, String urlKeyword, Integer page, Integer pagesize) {
        //创建分页对象，设置分页参数
        //注意：使用分页，需要配置分页插件
        IPage<UmsResource> pg=new Page<>(page,pagesize);
        LambdaQueryWrapper<UmsResource> wrapper =new LambdaQueryWrapper<>();

        wrapper.eq(categoryId != null,UmsResource::getCategoryId,categoryId);
        wrapper.like(nameKeyword != null,UmsResource::getName,nameKeyword);
        wrapper.like(urlKeyword != null,UmsResource::getUrl,urlKeyword);
        umsResourceMapper.selectPage(pg,wrapper);

        return pg;
    }

    @Override
    public void create(UmsResource umsResource) {
        umsResourceMapper.insert(umsResource);
    }

    @Override
    public void delete(Integer id) {
        umsResourceMapper.deleteById(id);
    }

    @Override
    public void update(UmsResource umsResource) {
        umsResourceMapper.updateById(umsResource);
    }

    @Override
    public List<UmsResource> findByIds(List<Long> resourceIds) {
        LambdaQueryWrapper<UmsResource> lqw = new LambdaQueryWrapper<>();
        lqw.in(UmsResource::getId, resourceIds);
        return umsResourceMapper.selectList(lqw);
    }

    @Override
    public List<UmsResource> findAll() {
        return umsResourceMapper.selectList(null);
    }
}
