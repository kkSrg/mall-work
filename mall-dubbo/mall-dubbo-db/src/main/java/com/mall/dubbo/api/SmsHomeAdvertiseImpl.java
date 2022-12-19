package com.mall.dubbo.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsHomeAdvertiseApi;
import com.mall.dubbo.mapper.SmsHomeAdvertiseMapper;
import com.mall.pojo.SmsHomeAdvertise;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsHomeAdvertiseImpl implements SmsHomeAdvertiseApi {
    @Autowired
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;

    @Override
    public void save(SmsHomeAdvertise smsHomeAdvertise) {
        smsHomeAdvertiseMapper.insert(smsHomeAdvertise);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        smsHomeAdvertiseMapper.deleteBatchIds(ids);
    }

    @Override
    public SmsHomeAdvertise getById(Integer id) {
        return smsHomeAdvertiseMapper.selectById(id);
    }

    @Override
    public void updateById(SmsHomeAdvertise advertise) {
        smsHomeAdvertiseMapper.updateById(advertise);
    }

    @Override
    public List<SmsHomeAdvertise> page(String endTime, String name, Integer type, Integer pageSize, Integer pageNum) {
        IPage<SmsHomeAdvertise> pg = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<SmsHomeAdvertise> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StrUtil.isNotBlank(name), SmsHomeAdvertise::getName, name);
        queryWrapper.like(StrUtil.isNotBlank(endTime), SmsHomeAdvertise::getEndTime, endTime);
        queryWrapper.like(null != type, SmsHomeAdvertise::getType, type);

        smsHomeAdvertiseMapper.selectPage(pg,queryWrapper);

        return pg.getRecords();
    }

    @Override
    public Integer finTotal(String endTime, String name, Integer type) {
        LambdaQueryWrapper<SmsHomeAdvertise> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(StrUtil.isNotBlank(endTime), SmsHomeAdvertise::getEndTime, endTime);
        queryWrapper.like(StrUtil.isNotBlank(name), SmsHomeAdvertise::getName, name);
        queryWrapper.like(null != type, SmsHomeAdvertise::getType, type);
        Integer count = smsHomeAdvertiseMapper.selectCount(queryWrapper);
        return count;
    }


}
