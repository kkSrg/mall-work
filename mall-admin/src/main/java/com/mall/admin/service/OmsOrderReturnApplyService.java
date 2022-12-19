package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.OrderReturnApplyApi;
import com.mall.dto.OmsOrderReturnApplyResult;
import com.mall.dto.OmsReturnApplyQueryParam;
import com.mall.dto.OmsUpdateStatusParam;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderReturnApply;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OmsOrderReturnApplyService {
    @DubboReference
    private OrderReturnApplyApi orderReturnApplyApi;


    public int delete(List<Long> ids) {
      return orderReturnApplyApi.delete(ids);
    }

    public CommonPage<OmsOrderReturnApply> getPage(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        CommonPage<OmsOrderReturnApply> result=new CommonPage<>();
        result.setTotalPage(1);
        result.setTotal(10);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        List<OmsOrderReturnApply> list=orderReturnApplyApi.getList(queryParam);
        result.setList(list);
        return result;
    }

    public OmsOrderReturnApplyResult getItem(Long id) {
        return orderReturnApplyApi.getDetail(id);
    }

    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        return orderReturnApplyApi.updateByPrimaryKeySelective(returnApply);
    }
}
