package com.mall.dto;

import com.mall.pojo.OmsCompanyAddress;
import com.mall.pojo.OmsOrderReturnApply;
import lombok.Data;


/**
 * 申请信息封装
 */
@Data
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {

    private OmsCompanyAddress companyAddress;//公司收货地址
}
