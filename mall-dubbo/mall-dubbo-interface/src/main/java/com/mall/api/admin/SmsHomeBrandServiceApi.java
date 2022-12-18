package com.mall.api.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.dto.UpdateRecommendStatusDTO;
import com.mall.pojo.SmsHomeBrand;

import java.util.List;

/**
 * 推荐品牌表
 *
 * @author 吴一飞
 * @date 2022/12/17 19:48
 */
public interface SmsHomeBrandServiceApi {
    /**
     * 添加首页推荐品牌
     */
    void save(SmsHomeBrand smsHomeBrand);

    /**
     * 批量删除推荐品牌
     */
    void deletes(List<Integer> ids);

    void recommendStatus(UpdateRecommendStatusDTO updateRecommendStatusDTO);

    void update(SmsHomeBrand smsHomeBrand);
    /**
     * 分页查询推荐品牌
     */
    IPage<SmsHomeBrand> getPage(Integer pageNum, Integer pageSize, String brandName, Integer recommendStatus);
}
