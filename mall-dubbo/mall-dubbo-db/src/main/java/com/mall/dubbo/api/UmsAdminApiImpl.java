package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.UmsAdminApi;
import com.mall.dubbo.mapper.UmsAdminMapper;
import com.mall.pojo.Admin;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsAdminApiImpl implements UmsAdminApi {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    /**
     * 登录功能
     */
    @Override
    public Admin login(String username, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername,username).eq(Admin::getPassword,password);

        return umsAdminMapper.selectOne(wrapper);
    }

    @Override
    public Admin findById(Long id) {
        return umsAdminMapper.selectById(id);
    }

    @Override
    public List<Admin> findAll(String keyword, Integer page, Integer pagesize) {
        //创建分页对象，设置分页参数
        //注意：使用分页，需要配置分页插件
        IPage<Admin> pg=new Page<>(page,pagesize);
        LambdaQueryWrapper<Admin> wrapper =new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)){
            wrapper.like(Admin::getUsername,keyword).or().like(Admin::getNickName,keyword);
        }
        umsAdminMapper.selectPage(pg, wrapper);
        return pg.getRecords();
    }
}
