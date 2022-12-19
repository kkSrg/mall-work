package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.OmsOrderReturnApplyService;
import com.mall.dto.OmsOrderReturnApplyResult;
import com.mall.dto.OmsReturnApplyQueryParam;
import com.mall.dto.OmsUpdateStatusParam;
import com.mall.pojo.OmsOrderReturnApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//订单退货申请处理
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    /**
     * 分页查询退货申请
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        CommonPage<OmsOrderReturnApply> page = returnApplyService.getPage(queryParam, pageSize, pageNum);
        return CommonResult.success(page);
    }


    /**
     * 批量删除退货申请
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("删除失败");
    }

    /**
     *获取退货申请详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
        return CommonResult.success(result);
    }


    @PostMapping(value = "/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.error("修改失败");
    }
}
