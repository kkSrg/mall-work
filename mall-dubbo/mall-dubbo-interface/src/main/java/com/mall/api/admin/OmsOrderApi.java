package com.mall.api.admin;

import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.pojo.OmsOrder;

import java.util.List;

public interface OmsOrderApi {


      int delivery(List<OmsOrderDeliveryParam> deliveryParamList);


      List<OmsOrder> getList(OmsOrderQueryParam queryParam);

      int deleteByExampleSelective(OmsOrder record, List<Long> ids);

      int updateDelivery(OmsOrder record, List<Long> ids);

      OmsOrderDetail gedetail(Long id);

      int updateByPrimaryKeySelective(OmsOrder order);
}
