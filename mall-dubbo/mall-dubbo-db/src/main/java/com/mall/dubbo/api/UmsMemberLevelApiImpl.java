package com.mall.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.admin.UmsMemberLevelApi;
import com.mall.dubbo.mapper.UmsMemberLevelMapper;
import com.mall.pojo.UmsMemberLevel;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMemberLevelApiImpl implements UmsMemberLevelApi {

    @Autowired
    private UmsMemberLevelMapper umsMemberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        LambdaQueryWrapper<UmsMemberLevel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null!=defaultStatus,UmsMemberLevel::getDefaultStatus,defaultStatus);
        return umsMemberLevelMapper.selectList(queryWrapper);
    }
}
