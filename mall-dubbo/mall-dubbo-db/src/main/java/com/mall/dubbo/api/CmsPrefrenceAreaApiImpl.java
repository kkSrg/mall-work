package com.mall.dubbo.api;

import com.mall.api.admin.CmsPrefrenceAreaApi;
import com.mall.dubbo.mapper.CmsPrefrenceAreaMapper;
import com.mall.pojo.CmsPrefrenceArea;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class CmsPrefrenceAreaApiImpl implements CmsPrefrenceAreaApi {

    @Autowired
    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return cmsPrefrenceAreaMapper.selectList(null);
    }
}
