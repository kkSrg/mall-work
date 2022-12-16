package com.mall.admin.service;

import com.mall.api.admin.UmsRoleApi;
import com.mall.pojo.UmsRole;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleService {

    @DubboReference
    private UmsRoleApi umsRoleApi;
    /**
     * 获取所有角色
     * @return
     */
    public List<UmsRole> listAll() {
        List<UmsRole> listAll = umsRoleApi.findAll();
        return listAll;
    }
}
