package com.mall.admin.service;

import cn.hutool.core.convert.Convert;
import com.mall.PageResult;
import com.mall.api.admin.PmsBrandApi;
import com.mall.exception.ConsumerException;
import com.mall.pojo.PmsBrand;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsBrandService {

    @DubboReference
    private PmsBrandApi pmsBrandApi;


    /**
     * 1.根据品牌名称分页获取品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult<PmsBrand> list(Integer pageNum, Integer pageSize) {
        PageResult<PmsBrand> result = new PageResult<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotalPage(1);
        result.setTotal(10);
        List<PmsBrand> list = pmsBrandApi.findAllBrands();
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
        Boolean flag = pmsBrandApi.updateFactoryStatus(factoryStatus,idList);
        if (!flag){
            throw new ConsumerException("批量更新厂家制造商状态失败");
        }
    }
}
