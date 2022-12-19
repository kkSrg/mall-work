package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.OmsCompanyAddressApi;
import com.mall.dubbo.mapper.OmsCompanyAddressMapper;
import com.mall.pojo.OmsCompanyAddress;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DubboService
public class OmsCompanyAddressApiImpl implements OmsCompanyAddressApi {

    @Autowired
    private OmsCompanyAddressMapper omsCompanyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        LambdaQueryWrapper<OmsCompanyAddress> queryWrapper = new LambdaQueryWrapper<>();
        return omsCompanyAddressMapper.selectList(queryWrapper);
    }
}
