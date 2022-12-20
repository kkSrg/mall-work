package com.mall.admin.service;

import com.mall.api.admin.UmsMemberLevelApi;
import com.mall.pojo.UmsMemberLevel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsMemberLevelService {

    @DubboReference
    private UmsMemberLevelApi umsMemberLevelApi;

    public List<UmsMemberLevel> list(Integer defaultStatus) {
        return umsMemberLevelApi.list(defaultStatus);
    }
}
