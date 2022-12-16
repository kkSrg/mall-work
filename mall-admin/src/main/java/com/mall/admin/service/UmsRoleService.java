package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import com.mall.api.admin.UmsRoleApi;
import com.mall.pojo.UmsRole;
import com.mall.vo.UmsRoleVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    public List<UmsRoleVo> listAll() {
        List<UmsRole> listAll = umsRoleApi.findAll();
        List<UmsRoleVo> voList = listAll.stream().map(umsRole -> {
            UmsRoleVo vo = new UmsRoleVo();
            BeanUtil.copyProperties(umsRole,vo);
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(umsRole.getCreateTime()));
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }
}
