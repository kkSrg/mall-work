package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.CmsSubjectApi;
import com.mall.dubbo.mapper.CmsSubjectMapper;
import com.mall.pojo.CmsSubject;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class CmsSubjectApiImpl implements CmsSubjectApi {
    @Autowired
    private CmsSubjectMapper cmsSubjectMapper;
    @Override
    public List<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum) {
        IPage<CmsSubject> pg = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<CmsSubject> queryWrapper = new LambdaQueryWrapper<>();

        //如果keyword存在则有条件查询，模糊查询
        queryWrapper.like(StrUtil.isNotBlank(keyword),CmsSubject::getTitle, keyword);

        cmsSubjectMapper.selectPage(pg,queryWrapper);

        return pg.getRecords();
    }

    @Override
    public Integer finTotal(String keyword) {
        LambdaQueryWrapper<CmsSubject> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(StrUtil.isNotBlank(keyword), CmsSubject::getTitle, keyword);
        Integer count = cmsSubjectMapper.selectCount(queryWrapper);
        return count;
    }

    @Override
    public List<CmsSubject> listAll() {
        return cmsSubjectMapper.selectList(null);
    }
}
