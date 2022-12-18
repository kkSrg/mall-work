package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.OmsOrderReturnReasonService;
import com.mall.pojo.OmsOrder;
import com.mall.pojo.OmsOrderReturnReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//退货原因设置
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonService orderReturnReasonService;

    /**
     * 分页查询退货原因
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        CommonPage<OmsOrderReturnReason> page =  orderReturnReasonService.getPage( pageSize, pageNum);
        return CommonResult.success(page);
    }

    /**
     * 添加退货原因
     * @param returnReason
     * @return
     */
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody OmsOrderReturnReason returnReason) {
        int count = orderReturnReasonService.create(returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("添加失败");
    }

    /**
     * 修改退货原因
     * @param id
     * @param returnReason
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderReturnReason returnReason) {
        int count = orderReturnReasonService.update(id, returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }

    /**
     * 获取单个退货原因详情信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason reason = orderReturnReasonService.getItem(id);
        return CommonResult.success(reason);
    }

    /**
     * 修改退货原因启用状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping(value = "/update/status")
    public CommonResult updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }

    /**
     * 批量删除退货原因
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("删除失败");
    }
}
