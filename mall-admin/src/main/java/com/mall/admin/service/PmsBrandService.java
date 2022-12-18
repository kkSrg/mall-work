package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.CommonPage;
import com.mall.api.admin.PmsBrandApi;
import com.mall.exception.ConsumerException;
import com.mall.pojo.PmsBrand;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsBrandService {

    @DubboReference
    private PmsBrandApi pmsBrandApi;


    /**
     * 1.根据品牌名称分页获取品牌列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    public CommonPage<PmsBrand> list(String keyword,Integer pageNum, Integer pageSize) {
        CommonPage<PmsBrand> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        IPage<PmsBrand> iPage = pmsBrandApi.findABrandsByKeyWord(keyword, pageNum, pageSize);
        List<PmsBrand> list = iPage.getRecords();
        result.setTotal(Convert.toInt(iPage.getTotal()));
        result.setTotalPage(Convert.toInt(iPage.getPages()));
        result.setList(list);
        return result;
    }

    /**
     * 2.添加品牌
     * @return
     */
    public void create(PmsBrand brand) {
        Boolean flag = pmsBrandApi.save(brand);
        if (!flag){
            throw new ConsumerException("添加品牌失败");
        }
    }

    /**
     * 3.获取全部品牌列表
     * @return
     */
    public List<PmsBrand> listAll() {
        return pmsBrandApi.findAllBrands();
    }

    /**
     * 4.删除品牌
     * @param id
     * @return
     */
    public void deleteById(Long id) {
        Boolean flag = pmsBrandApi.deleteById(id);
        if (!flag){
            throw new ConsumerException("删除品牌失败");
        }
    }

    /**
     * 5.批量删除品牌
     * @param ids
     * @return
     */
    public void deleteByIds(List<Integer> ids) {
        Boolean flag = pmsBrandApi.deleteByIds(ids);
        if (!flag){
            throw new ConsumerException("批量删除品牌失败");
        }
    }

    /**
     * 6.根据编号查询品牌信息
     * @param id
     * @return
     */
    public PmsBrand getMsgById(Long id) {
        return pmsBrandApi.getMsgById(id);
    }

    /**
     * 7.批量更新厂家制造商状态
     * @return
     */
    public void updateFactoryStatus(Integer factoryStatus, List<Integer> ids) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsBrandApi.updateFactoryStatus(factoryStatus,idList);
    }

    /**
     * 8.批量更新显示状态
     * @return
     */
    public void updateShowStatus(Integer showStatus, List<Integer> ids) {
        List<Long> idList = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        pmsBrandApi.updateShowStatus(showStatus,idList);
    }

    /**
     * 9.更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    public void updateById(Integer id, PmsBrand pmsBrandParam) {
        Boolean flag = pmsBrandApi.updateById(Convert.toLong(id),pmsBrandParam);
        if (!flag){
            throw new ConsumerException("品牌信息修改失败");
        }
    }
}
