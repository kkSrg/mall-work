package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.OrderReturnReasonApi;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderReturnReason;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OmsOrderReturnReasonService {
    @DubboReference
    private OrderReturnReasonApi orderReturnReasonApi;
    public CommonPage<OmsOrderReturnReason> getPage(Integer pageSize, Integer pageNum) {
        CommonPage<OmsOrderReturnReason> result=new CommonPage<>();
        result.setTotalPage(1);
        result.setTotal(10);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        List<OmsOrderReturnReason> list=orderReturnReasonApi.getList();
        result.setList(list);
        return result;
    }
    /**
     * 添加退货原因
     * @param returnReason
     * @return
     */
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return orderReturnReasonApi.insert(returnReason);
    }
    /**
     * 修改退货原因
     * @param id
     * @param returnReason
     * @return
     */
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return orderReturnReasonApi.updateByPrimaryKey(returnReason);
    }
    /**
     * 获取单个退货原因详情信息
     * @param id
     * @return
     */
    public OmsOrderReturnReason getItem(Long id) {
        return orderReturnReasonApi.selectByPrimaryKey(id);
    }

    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }

        return orderReturnReasonApi.updateStatus(ids,status);
    }

    public int delete(List<Long> ids) {
        return orderReturnReasonApi.delete(ids);
    }
}
