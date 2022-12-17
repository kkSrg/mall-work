package com.mall.api.admin;

import com.mall.pojo.PmsBrand;

import java.util.List;

public interface PmsBrandApi {

    //获取全部品牌
    List<PmsBrand> findAllBrands();

    //添加品牌
    Boolean save(PmsBrand brand);

    //删除品牌
    Boolean deleteById(Long id);

    //批量删除品牌
    Boolean deleteByIds(List<Integer> ids);

    //根据编号查询品牌信息
    PmsBrand getMsgById(Long id);

    //批量更新厂家制造商状态
    void updateFactoryStatus(Integer factoryStatus, List<Long> idList);

    //批量更新显示状态
    void updateShowStatus(Integer showStatus, List<Long> idList);

    //更新品牌
    Boolean updateById(Long toLong, PmsBrand pmsBrandParam);

    //模糊查询得到的数据总条数
    Integer selectCountByKeyWord(String keyword);

    //模糊查询得到的品牌
    List<PmsBrand> findABrandsByKeyWord(String keyword);
}
