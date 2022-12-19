package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.OmsCompanyAddressService;
import com.mall.pojo.OmsCompanyAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//获取所有收货地址
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    private OmsCompanyAddressService companyAddressService;

    /**
     * 获取所有收货地址
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }
}
