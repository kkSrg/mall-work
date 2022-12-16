package com.mall.admin.controller;

import cn.hutool.core.convert.Convert;
import com.mall.PageResult;
import com.mall.R;
import com.mall.admin.service.PmsBrandService;
import com.mall.pojo.PmsBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResult<PmsBrand>> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        PageResult<PmsBrand> result = pmsBrandService.list(pageNum,pageSize);
        return ResponseEntity.ok(result);
    }

    /**
     * 2.添加品牌
     * @return
     */
    @PostMapping("/create")
    public R<String> create(@RequestBody PmsBrand brand){
        pmsBrandService.create(brand);
        return R.success("品牌添加成功");
    }

    /**
     * 3.获取全部品牌列表
     * @return
     */
    @GetMapping("/listAll")
    public R<List<PmsBrand>> listAll(){
        List<PmsBrand> list = pmsBrandService.listAll();
        return R.success(list);
    }

    /**
     * 4.删除品牌
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public R<String> delete(@PathVariable Integer id){
        pmsBrandService.deleteById(Convert.toLong(id)); //前端传过来的是Integer类型,实体类封装为Long类型
        return R.success("品牌删除成功");
    }

    /**
     * 5.批量删除品牌
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public R<String> deleteBatch(@RequestParam List<Integer> ids){
        pmsBrandService.deleteByIds(ids);
        return R.success("批量删除品牌成功");
    }

    /**
     * 6.根据编号查询品牌信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<PmsBrand> getMsgById(@PathVariable Integer id){
        PmsBrand brand = pmsBrandService.getMsgById(Convert.toLong(id));
        return R.success(brand);
    }

    /**
     * 7.批量更新厂家制造商状态
     * @return
     */
    @PostMapping("/update/factoryStatus")
    public R<String> updateFactoryStatus(@RequestParam Integer factoryStatus,@RequestParam List<Integer> ids){
        pmsBrandService.updateFactoryStatus(factoryStatus,ids);
        return R.success("修改成功");
    }

}
