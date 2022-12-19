package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.OmsOrderService;
import com.mall.dto.*;
import com.mall.pojo.OmsOrder;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 获取订单详情：订单信息、商品信息、操作记录
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }

    /**
     * 修改收货人信息
     * @param receiverInfoParam
     * @return
     */
    @PostMapping(value = "/update/receiverInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }

    /**
     * 备注订单
     * @param id
     * @param note
     * @param status
     * @return
     */
    @PostMapping(value = "/update/note")
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, note, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("备注失败");
    }


    /**
     *修改订单费用信息
     * @param moneyInfoParam
     * @return
     */
 @PostMapping("/update/moneyInfo")
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }

}
