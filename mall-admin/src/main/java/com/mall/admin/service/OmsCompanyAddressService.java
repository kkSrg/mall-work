package com.mall.admin.service;

import com.mall.api.admin.OmsCompanyAddressApi;
import com.mall.pojo.OmsCompanyAddress;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmsCompanyAddressService {

    @DubboReference
    private OmsCompanyAddressApi omsCompanyAddressApi;
    /**
     * 获取所有收货地址
     * @return
     */
    public List<OmsCompanyAddress> list() {
       return omsCompanyAddressApi.list();
    }
}
