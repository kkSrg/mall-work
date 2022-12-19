package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsHomeRecommendSubjectApi;
import com.mall.dubbo.mapper.SmsHomeRecommendSubjectMapper;
import com.mall.pojo.SmsHomeRecommendProduct;
import com.mall.pojo.SmsHomeRecommendSubject;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsHomeRecommendSubjectImpl implements SmsHomeRecommendSubjectApi {

    @Autowired
    private SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;
    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        IPage<SmsHomeRecommendSubject> pg = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<SmsHomeRecommendSubject> queryWrapper = new LambdaQueryWrapper<>();
        //查询全部按照sort升序
        queryWrapper.orderByAsc(SmsHomeRecommendSubject::getSort);

        //如果keyword存在则有条件查询，模糊查询
        queryWrapper.like(StrUtil.isNotBlank(subjectName), SmsHomeRecommendSubject::getSubjectName, subjectName);
        queryWrapper.like(recommendStatus != null , SmsHomeRecommendSubject::getRecommendStatus, recommendStatus);

        smsHomeRecommendSubjectMapper.selectPage(pg, queryWrapper);

        return pg.getRecords();
    }

    @Override
    public Integer finTotal(String subjectName, Integer recommendStatus) {
        LambdaQueryWrapper<SmsHomeRecommendSubject> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(StrUtil.isNotBlank(subjectName), SmsHomeRecommendSubject::getSubjectName, subjectName);
        queryWrapper.like(null != recommendStatus, SmsHomeRecommendSubject::getRecommendStatus, recommendStatus);
        Integer count = smsHomeRecommendSubjectMapper.selectCount(queryWrapper);
        return count;
    }

    @Override
    public int deleteIds(List<Long> ids) {
        int count = 0;
        for (Long id : ids) {
            smsHomeRecommendSubjectMapper.deleteById(id);
            count++;
        }
        return count;
    }


    @Override
    public int updateCommend(List<Long> ids, Integer recommendStatus) {
        int count = 0;
        LambdaQueryWrapper<SmsHomeRecommendSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SmsHomeRecommendSubject::getId, ids);
        List<SmsHomeRecommendSubject> SmsHomeRecommendSubjectList = smsHomeRecommendSubjectMapper.selectList(queryWrapper);
        for (SmsHomeRecommendSubject smsHomeRecommendProduct : SmsHomeRecommendSubjectList) {
            smsHomeRecommendProduct.setRecommendStatus(recommendStatus);
            smsHomeRecommendSubjectMapper.updateById(smsHomeRecommendProduct);
            count++;
        }

        return count;
    }


    @Override
    public void updateSort(Integer id, Integer sort) {
        SmsHomeRecommendSubject smsHomeRecommendSubject = new SmsHomeRecommendSubject();
        SmsHomeRecommendSubject smsHomeRecommendSubject1 = smsHomeRecommendSubjectMapper.selectById(id);
        smsHomeRecommendSubject.setId(id);
        smsHomeRecommendSubject.setSort(sort);
        smsHomeRecommendSubject.setRecommendStatus(smsHomeRecommendSubject1.getRecommendStatus());
        smsHomeRecommendSubjectMapper.updateById(smsHomeRecommendSubject);
    }

    @Override
    public void create(List<SmsHomeRecommendSubject> SmsHomeRecommendSubjectList) {
        for (SmsHomeRecommendSubject smsHomeRecommendSubject : SmsHomeRecommendSubjectList) {
            smsHomeRecommendSubjectMapper.insert(smsHomeRecommendSubject);
        }
    }
}
