package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.admin.service.SmsFlashPromotionSessionService;
import com.mall.pojo.SmsFlashPromotionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 场次表
 *
 * @author 吴一飞
 * @date 2022/12/16 21:49
 */

@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Autowired
    private SmsFlashPromotionSessionService smsFlashPromotionSessionService;

    /**
     * 获取全部场次
     * @return
     */
    @GetMapping("/list")
    private CommonResult<List<SmsFlashPromotionSession>> list(){
        List<SmsFlashPromotionSession> list = smsFlashPromotionSessionService.list();
        return CommonResult.success(list);
    }


    /**
     * 添加场次
     * @return
     */
    @PostMapping("/create")
    private CommonResult<Object> create(@RequestBody SmsFlashPromotionSession smsFlashPromotionSession){
        smsFlashPromotionSessionService.create(smsFlashPromotionSession);
        return CommonResult.success(null);
    }

    /**
     * 删除场次
     * @return
     */
    @PostMapping("/delete/{id}")
    private CommonResult<Object> deleteById(@PathVariable Long id){
        smsFlashPromotionSessionService.deleteById(id);
        return CommonResult.success(null);
    }

    /**
     * 修改启用状态
     * @param id
     * @param smsFlashPromotionSession
     * @return
     */
    @PostMapping("/update/status/{id}")
    private CommonResult<Object> updateStatus(@PathVariable Long id,@RequestBody SmsFlashPromotionSession smsFlashPromotionSession){
        smsFlashPromotionSession.setId(id);
        smsFlashPromotionSessionService.updateStatus(smsFlashPromotionSession);
        return CommonResult.success(null);
    }


    /**
     * 修改场次
     * @param id
     * @param smsFlashPromotionSession
     * @return
     */
    @PostMapping("/update/{id}")
    private CommonResult<Object> updateById(@PathVariable Long id,@RequestBody SmsFlashPromotionSession smsFlashPromotionSession){
        smsFlashPromotionSession.setId(id);
        smsFlashPromotionSessionService.updateStatus(smsFlashPromotionSession);
        return CommonResult.success(null);
    }

}
