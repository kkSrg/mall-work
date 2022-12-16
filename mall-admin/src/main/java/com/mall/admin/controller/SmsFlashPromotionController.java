package com.mall.admin.controller;


import com.mall.CommonPage;
import com.mall.admin.service.SmsFlashPromotionService;
import com.mall.pojo.SmsFlashPromotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀活动列表
 *
 * @author 吴一飞
 * @date 2022/12/16 15:10
 */
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashPromotionService smsFlashPromotionService;


    /**
     * 根据活动名称分页查询
     *
     * @author 吴一飞
     * @date 2022/12/16 15:20
     */
    @GetMapping("/list")
    public ResponseEntity<CommonPage<SmsFlashPromotion>> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize, String keyword) {
        //调用service获取分页
        CommonPage<SmsFlashPromotion> page = smsFlashPromotionService.getPage(pageNum,pageSize,keyword);

        return ResponseEntity.ok(page);
    }


    /**
     * 添加活动
     * @param smsFlashPromotion
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody SmsFlashPromotion smsFlashPromotion){
        smsFlashPromotionService.create(smsFlashPromotion);
        return ResponseEntity.ok(null);
    }


    /**
     * 根据id删除活动
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        smsFlashPromotionService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    /**
     * 根据id修改上下线状态
     * @param id
     * @return
     */
    @PostMapping("/update/status/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id,@RequestBody Integer status){
        smsFlashPromotionService.updateStatus(id,status);
        return ResponseEntity.ok(null);
    }

    /**
     * 编辑活动
     * @return
     */
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateId(@PathVariable Long id,@RequestBody SmsFlashPromotion smsFlashPromotion){
        smsFlashPromotion.setId(id);
        smsFlashPromotionService.updateId(smsFlashPromotion);
        return ResponseEntity.ok(null);
    }

    /**
     * 获取活动详情
     * @param id
     * @return
     */
    @GetMapping("/flash/{id}")
    public ResponseEntity<SmsFlashPromotion> eventDetails(@PathVariable Long id) {
        SmsFlashPromotion smsFlashPromotion = smsFlashPromotionService.eventDetails(id);
        return ResponseEntity.ok(smsFlashPromotion);
    }
}
