package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.UmsMenuApi;
import com.mall.dubbo.mapper.UmsMenuMapper;
import com.mall.pojo.PmsBrand;
import com.mall.pojo.UmsMenu;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMenuApiImpl implements UmsMenuApi {

    @Autowired
    private UmsMenuMapper umsMenuMapper;

    @Override
    public List<UmsMenu> findAll() {
        return umsMenuMapper.selectList(null);
    }

    @Override
    public IPage<UmsMenu> getPageByParentId(Integer parentId, Integer page, Integer pagesize) {
        //创建分页对象，设置分页参数
        //注意：使用分页，需要配置分页插件
        IPage<UmsMenu> pg = new Page<>(page, pagesize);
        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsMenu::getParentId,parentId);
        umsMenuMapper.selectPage(pg,wrapper);
        return pg;
    }

    @Override
    public void update(Integer id, UmsMenu umsMenu) {
        umsMenuMapper.updateById(umsMenu);
    }

    @Override
    public UmsMenu findById(Integer id) {
        return umsMenuMapper.selectById(id);
    }

    @Override
    public void create(UmsMenu umsMenu) {
        umsMenuMapper.insert(umsMenu);
    }

    @Override
    public void delete(Integer id) {
        umsMenuMapper.deleteById(id);
    }
}
