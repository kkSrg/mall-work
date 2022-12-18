package com.mall.dubbo.api;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.SmsHomeBrandServiceApi;
import com.mall.dto.UpdateRecommendStatusDTO;
import com.mall.dubbo.mapper.SmsHomeBrandMapper;
import com.mall.pojo.SmsHomeBrand;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsHomeBrandServiceApiImpl implements SmsHomeBrandServiceApi {
    @Autowired
    private SmsHomeBrandMapper smsHomeBrandMapper;

    /**
     * 分页查询推荐品牌
     */
    @Override
    public IPage<SmsHomeBrand> getPage(Integer pageNum, Integer pageSize, String brandName, Integer recommendStatus) {

        IPage<SmsHomeBrand> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<SmsHomeBrand> wrapper = new LambdaQueryWrapper<>();
        //设置排序条件 根据状态升序
        wrapper.orderByDesc(SmsHomeBrand::getSort);
        //如果两个都为null 查所有分页
        if (ObjectUtil.isEmpty(brandName) && ObjectUtil.isEmpty(recommendStatus)){
            smsHomeBrandMapper.selectPage(page,wrapper);
        }else if (ObjectUtil.isEmpty(brandName) && ObjectUtil.isNotEmpty(recommendStatus)){
            //recommendStatus不为bull 根据recommendStatus查询分页
            wrapper.like(SmsHomeBrand::getRecommendStatus,recommendStatus);
            smsHomeBrandMapper.selectPage(page,wrapper);
        }else if (ObjectUtil.isNotEmpty(brandName) && ObjectUtil.isEmpty(recommendStatus)){
            //brandName不为bull 根据brandName查询分页
            wrapper.like(SmsHomeBrand::getBrandName,brandName);
            smsHomeBrandMapper.selectPage(page,wrapper);
        }else {
            //两个都不为null 则两个条件都要
            wrapper.like(SmsHomeBrand::getRecommendStatus,recommendStatus)
                    .like(SmsHomeBrand::getBrandName,brandName);
            smsHomeBrandMapper.selectPage(page,wrapper);
        }
        return page;
    }

    /**
     * 添加首页推荐品牌
     */
    @Override
    public void save(SmsHomeBrand smsHomeBrand) {
        smsHomeBrandMapper.insert(smsHomeBrand);
    }
    /**
     * 批量删除推荐品牌
     */
    @Override
    public void deletes(List<Integer> ids) {
        smsHomeBrandMapper.deleteBatchIds(ids);
    }
    /**
     * 批量修改推荐品牌状态
     */
    @Override
    public void recommendStatus(UpdateRecommendStatusDTO updateRecommendStatusDTO) {
        List<Integer> ids = updateRecommendStatusDTO.getIds();
        Integer recommendStatus = updateRecommendStatusDTO.getRecommendStatus();
        SmsHomeBrand smsHomeBrand = new SmsHomeBrand();
        smsHomeBrand.setRecommendStatus(recommendStatus);
        for (Integer id : ids) {
            smsHomeBrand.setId(Long.valueOf(id));
            smsHomeBrandMapper.updateById(smsHomeBrand);
        }
    }

    @Override
    public void update(SmsHomeBrand smsHomeBrand) {
        smsHomeBrandMapper.updateById(smsHomeBrand);
    }

}
