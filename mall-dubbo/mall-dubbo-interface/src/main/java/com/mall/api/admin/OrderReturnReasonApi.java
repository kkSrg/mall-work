package com.mall.api.admin;

import com.mall.CommonPage;
import com.mall.pojo.OmsOrderReturnReason;

import java.util.List;

public interface OrderReturnReasonApi {
    List<OmsOrderReturnReason> getList();

    int insert(OmsOrderReturnReason returnReason);

    int updateByPrimaryKey(OmsOrderReturnReason returnReason);

    OmsOrderReturnReason selectByPrimaryKey(Long id);

    int updateStatus(List<Long> ids,Integer status);

    int delete(List<Long> ids);

    CommonPage<OmsOrderReturnReason> getPage(Integer pageNum, Integer pageSize);
}
