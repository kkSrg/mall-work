package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.OmsOrderService;
import com.mall.dto.OmsOrderDeliveryParam;
import com.mall.dto.OmsOrderDetail;
import com.mall.dto.OmsOrderQueryParam;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.SmsFlashPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    /**
     * 查看订单
     * @param queryParam
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam, @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSzie",required = false,defaultValue = "10")Integer pageSize){
        CommonPage<OmsOrder> page = orderService.getPage(queryParam, pageSize, pageNum);
        return CommonResult.success(page);
    }

    /**
     * 批量发货
     * @param deliveryParamList
     * @return
     */
    @PostMapping(value = "/update/delivery")
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("失败");
    }

    /**
     * 批量关闭订单
     * @param ids
     * @param note
     * @return
     */
    @PostMapping(value = "/update/close")
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = orderService.close(ids, note);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("失败");
    }

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("失败");
    }

    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }



}
