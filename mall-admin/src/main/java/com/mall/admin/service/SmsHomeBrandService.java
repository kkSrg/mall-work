package com.mall.admin.service;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.CommonPage;
import com.mall.api.admin.SmsHomeBrandServiceApi;
import com.mall.dto.UpdateRecommendStatusDTO;
import com.mall.pojo.SmsHomeBrand;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 推荐品牌表
 *
 * @author 吴一飞
 * @date 2022/12/17 19:48
 */
@Service
public class SmsHomeBrandService {

    @DubboReference
    private SmsHomeBrandServiceApi smsHomeBrandServiceApi;

    private Integer RECOMMEND = 1;

    /**
     * 分页查询推荐品牌
     */
    public CommonPage<SmsHomeBrand> smsHomeBrandPage(Integer pageNum, Integer pageSize, String brandName, Integer recommendStatus) {
        CommonPage<SmsHomeBrand> result = new CommonPage<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);

        IPage<SmsHomeBrand> page = smsHomeBrandServiceApi.getPage(pageNum,pageSize,brandName, recommendStatus);
        result.setList(page.getRecords());

        result.setTotal(Convert.toInt(page.getTotal()));
        double ceil = Math.ceil(Convert.toDouble(page.getTotal()) / Convert.toDouble(pageSize));
        result.setTotalPage(Convert.toInt(ceil));

        return result;
    }

    /**
     * 添加首页推荐品牌
     */
    public void addSmsHomeBrand(SmsHomeBrand smsHomeBrand) {
        smsHomeBrandServiceApi.save(smsHomeBrand);
    }

    /**
     * 批量删除推荐品牌
     */
    public void deletes(List<Integer> ids) {
        smsHomeBrandServiceApi.deletes(ids);
    }

    /**
     * 批量修改推荐品牌状态
     */
    public void recommendStatus(UpdateRecommendStatusDTO updateRecommendStatusDTO) {
        smsHomeBrandServiceApi.recommendStatus(updateRecommendStatusDTO);
    }
    /**
     * 修改排序
     */
    public void updateSort(Long id, Integer sort) {

        SmsHomeBrand smsHomeBrand = new SmsHomeBrand();
        smsHomeBrand.setId(id);
        smsHomeBrand.setSort(sort);

        smsHomeBrandServiceApi.update(smsHomeBrand);
    }
}
