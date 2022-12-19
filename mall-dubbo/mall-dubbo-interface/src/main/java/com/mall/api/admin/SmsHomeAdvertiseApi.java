package com.mall.api.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.pojo.SmsHomeAdvertise;

import java.util.List;

public interface SmsHomeAdvertiseApi {
    void save(SmsHomeAdvertise smsHomeAdvertise);

    void removeByIds(List<Long> ids);


    SmsHomeAdvertise getById(Integer id);

    void updateById(SmsHomeAdvertise advertise);

    List<SmsHomeAdvertise> page(String endTime, String name, Integer type, Integer pageSize, Integer pageNum);

    Integer finTotal(String endTime, String name, Integer type);
}
