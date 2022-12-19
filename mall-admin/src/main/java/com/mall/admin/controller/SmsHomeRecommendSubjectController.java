package com.mall.admin.controller;

import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.SmsHomeRecommendSubjectService;
import com.mall.pojo.SmsHomeRecommendProduct;
import com.mall.pojo.SmsHomeRecommendSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService smsHomeRecommendSubjectService;
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName",required = false)String subjectName,
                                                                  @RequestParam(value = "recommendStatus",required = false)Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                                                  @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
        CommonPage<SmsHomeRecommendSubject>  result = smsHomeRecommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(result);
    }

    //批量删除
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = smsHomeRecommendSubjectService.delete(ids);
        if (count>0){
            return CommonResult.success("删除成功");
        }
        return CommonResult.error("删除失败");
    }

    //批量修改推荐状态
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommend(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus){
        int count = smsHomeRecommendSubjectService.updateCommend(ids,recommendStatus);

        if (count>0){
            return CommonResult.success("修改成功");
        }
        return CommonResult.success("修改失败");
    }

    //修改推荐排序
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable Integer id ,Integer sort){
        smsHomeRecommendSubjectService.updateSort(id,sort);
        return CommonResult.success(null);
    }

    //首页添加商品
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendSubject> smsHomeRecommendSubject){
        smsHomeRecommendSubjectService.create(smsHomeRecommendSubject);
        return CommonResult.success(null);
    }
}
