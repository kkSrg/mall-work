package com.mall.admin.controller;

import cn.hutool.core.convert.Convert;
import com.mall.CommonPage;
import com.mall.CommonResult;
import com.mall.admin.service.PmsBrandService;
import com.mall.pojo.PmsBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    /**
     * 1.根据品牌名称分页获取品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam String keyword,
                                                   @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        CommonPage<PmsBrand> result = pmsBrandService.list(keyword,pageNum,pageSize);
        return CommonResult.success(result);
    }

    /**
     * 2.添加品牌
     * @return
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody PmsBrand brand){
        pmsBrandService.create(brand);
        return CommonResult.success("品牌添加成功");
    }

    /**
     * 3.获取全部品牌列表
     * @return
     */
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAll(){
        List<PmsBrand> list = pmsBrandService.listAll();
        return CommonResult.success(list);
    }

    /**
     * 4.删除品牌
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public CommonResult<String> delete(@PathVariable Integer id){
        pmsBrandService.deleteById(Convert.toLong(id)); //前端传过来的是Integer类型,实体类封装为Long类型
        return CommonResult.success("品牌删除成功");
    }

    /**
     * 5.批量删除品牌
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public CommonResult<String> deleteBatch(@RequestParam List<Integer> ids){
        pmsBrandService.deleteByIds(ids);
        return CommonResult.success("批量删除品牌成功");
    }

    /**
     * 6.根据编号查询品牌信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> getMsgById(@PathVariable Integer id){
        PmsBrand brand = pmsBrandService.getMsgById(Convert.toLong(id));
        return CommonResult.success(brand);
    }

    /**
     * 7.批量更新厂家制造商状态
     * @return
     */
    @PostMapping("/update/factoryStatus")
    public CommonResult<String> updateFactoryStatus(@RequestParam Integer factoryStatus, @RequestParam List<Integer> ids){
        pmsBrandService.updateFactoryStatus(factoryStatus,ids);
        return CommonResult.success("修改成功");
    }

    /**
     * 8.批量更新显示状态
     * @return
     */
    @PostMapping("/update/showStatus")
    public CommonResult<String> updateShowStatus(@RequestParam Integer showStatus, @RequestParam List<Integer> ids){
        pmsBrandService.updateShowStatus(showStatus,ids);
        return CommonResult.success("修改成功");
    }

    /**
     * 9.更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    @PostMapping("/update/{id}")
    public CommonResult<String> updateById(@PathVariable Integer id, @RequestBody PmsBrand pmsBrandParam){
        pmsBrandService.updateById(id,pmsBrandParam);
        return CommonResult.success("修改成功");
    }
}
