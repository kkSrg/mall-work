package com.mall.api.admin;


import com.mall.CommonPage;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.pojo.OmsOrder;

import java.util.List;

public interface OmsOrderApi {


      void delivery(List<OmsOrderDeliveryParam> deliveryParamList);



      List<OmsOrder> getList(OmsOrderQueryParam queryParam, Integer pageNum, Integer pageSize, CommonPage<OmsOrder> result);

      int deleteByExampleSelective(OmsOrder record, List<Long> ids);

      int updateDelivery(OmsOrder record, List<Long> ids);

      OmsOrderDetail gedetail(Long id);

      int updateByPrimaryKeySelective(OmsOrder order);


      CommonPage<OmsOrder> getPage( OmsOrderQueryParam queryParam, Integer pageNum, Integer pageSize);
}
