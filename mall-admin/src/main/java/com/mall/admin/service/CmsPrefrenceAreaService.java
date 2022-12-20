package com.mall.admin.service;

import com.mall.api.admin.CmsPrefrenceAreaApi;
import com.mall.pojo.CmsPrefrenceArea;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsPrefrenceAreaService {

    @DubboReference
    private CmsPrefrenceAreaApi cmsPrefrenceAreaApi;

    public List<CmsPrefrenceArea> listAll() {
        return cmsPrefrenceAreaApi.listAll();
    }
}

