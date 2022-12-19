package com.mall.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.admin.controller.OssController;
import com.mall.api.admin.SmsHomeAdvertiseApi;
import com.mall.autoconfig.template.OssTemplate;
import com.mall.pojo.SmsHomeAdvertise;
import com.mall.pojo.SmsHomeRecommendProduct;
import com.mall.pojo.SmsHomeRecommendSubject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsHomeAdvertiseService {
    @DubboReference
    private SmsHomeAdvertiseApi smsHomeAdvertiseApi;

    @Autowired
    private OssTemplate ossTemplate;
    public void save(SmsHomeAdvertise smsHomeAdvertise) {
        smsHomeAdvertise.setPic(smsHomeAdvertise.getNote());
        smsHomeAdvertise.setClickCount(0);
        smsHomeAdvertise.setOrderCount(0);
        smsHomeAdvertiseApi.save(smsHomeAdvertise);
    }

    public void removeByIds(List<Long> ids) {
        smsHomeAdvertiseApi.removeByIds(ids);
    }



    public SmsHomeAdvertise getById(Integer id) {
        return smsHomeAdvertiseApi.getById(id);
    }

    public void updateById(SmsHomeAdvertise advertise) {
        smsHomeAdvertiseApi.updateById(advertise);
    }

    public CommonPage<SmsHomeAdvertise> page(String endTime, String name, Integer type, Integer pageSize, Integer pageNum) {
        //查询数据到分页的records中返回到service
        List<SmsHomeAdvertise> pageInfoList = smsHomeAdvertiseApi.page(endTime,name,type,pageSize,pageNum);
        Integer total = smsHomeAdvertiseApi.finTotal(endTime,name,type);
        CommonPage<SmsHomeAdvertise> result = new CommonPage<>();
        //封装5个中的两个
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setList(pageInfoList);
        result.setTotal(total);
        Integer totalPage = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        result.setTotalPage(totalPage);
        return result;
    }
}
