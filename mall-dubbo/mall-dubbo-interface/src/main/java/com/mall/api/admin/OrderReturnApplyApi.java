package com.mall.api.admin;

import com.mall.CommonPage;
import com.mall.dto.OmsOrderReturnApplyResult;
import com.mall.dto.OmsReturnApplyQueryParam;
import com.mall.pojo.OmsOrderReturnApply;

import java.util.List;

public interface OrderReturnApplyApi {
    List<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam queryParam);

    OmsOrderReturnApplyResult getDetail(Long id);

   int delete(List<Long> ids);

    int updateByPrimaryKeySelective(OmsOrderReturnApply returnApply);

    CommonPage<OmsOrderReturnApply> getPage(OmsReturnApplyQueryParam queryParam, Integer pageNum, Integer pageSize);
}
