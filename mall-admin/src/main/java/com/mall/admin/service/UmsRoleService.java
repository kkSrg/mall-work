package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.mall.api.admin.UmsRoleApi;
import com.mall.pojo.UmsRole;
import com.mall.vo.UmsRoleVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleService {

    @DubboReference
    private UmsRoleApi umsRoleApi;

    /**
     * 获取所有角色
     *
     * @return
     */
    public List<UmsRoleVo> listAll() {
        List<UmsRole> listAll = umsRoleApi.findAll();
        //该用户下没有角色休息,返回空集合,前端才不会报错
        if (CollUtil.isEmpty(listAll)) {
            return new ArrayList<>();
        }
        List<UmsRoleVo> voList = listAll.stream().map(umsRole -> {
            UmsRoleVo vo = new UmsRoleVo();
            BeanUtil.copyProperties(umsRole, vo);
            //转为UTC时间格式
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SS:SS").format(umsRole.getCreateTime()));
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }
}
