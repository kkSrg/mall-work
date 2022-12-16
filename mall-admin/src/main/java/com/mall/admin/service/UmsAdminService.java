package com.mall.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.mall.CommonPage;
import com.mall.api.admin.UmsAdminApi;
import com.mall.pojo.Admin;
import com.mall.vo.AdminVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsAdminService {

    @DubboReference
    private UmsAdminApi umsAdminApi;


    /**
     * 根据用户名或姓名分页获取用户列表
     * @param keyword
     * @param page
     * @param pagesize
     */
    public CommonPage<AdminVo> adminsByKw(String keyword, Integer page, Integer pagesize) {
        CommonPage<AdminVo> listPage = new CommonPage<>();
        listPage.setPageNum(page);
        listPage.setPageSize(pagesize);

        List<Admin> list = umsAdminApi.findAll(keyword,page,pagesize);
        List<AdminVo> adminVoList = list.stream().map(admin -> {
            AdminVo vo = new AdminVo();
            BeanUtil.copyProperties(admin,vo);
            vo.setCreateTime(admin.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()):null);
            vo.setLoginTime(admin.getLoginTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(admin.getCreateTime()):null);
            return vo;
        }).collect(Collectors.toList());
        listPage.setList(adminVoList);
        listPage.setTotal(adminVoList.size());
        listPage.setTotalPage((int) (adminVoList.size() / pagesize + ((adminVoList.size() % pagesize == 0) ? 0 : 1)));
        return listPage;
    }
}
