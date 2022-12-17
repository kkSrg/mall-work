package com.mall.api.admin;

import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderExample;

import java.util.List;

public interface OmsOrderApi {


    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    int updateByExampleSelective(OmsOrder record, OmsOrderExample example);

    List<OmsOrder> getList(OmsOrderQueryParam queryParam);
}
