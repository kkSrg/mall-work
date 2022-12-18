package com.mall.dubbo.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.admin.PmsBrandApi;
import com.mall.dubbo.mapper.PmsBrandMapper;
import com.mall.pojo.Admin;
import com.mall.pojo.PmsBrand;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class PmsBrandApiImpl implements PmsBrandApi {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    //获取全部品牌
    @Override
    public List<PmsBrand> findAllBrands() {
        return pmsBrandMapper.selectList(null);
    }

    //添加品牌
    @Override
    public Boolean save(PmsBrand brand) {
        int count = pmsBrandMapper.insert(brand);
        return count != 0;
    }

    //删除品牌
    @Override
    public Boolean deleteById(Long id) {
        int count = pmsBrandMapper.deleteById(id);
        return count != 0;
    }

    //批量删除品牌
    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        List<Long> collect = ids.stream().map(id -> {
            return Convert.toLong(id);
        }).collect(Collectors.toList());
        int count = pmsBrandMapper.deleteBatchIds(collect);
        return count != 0;
    }

    //根据编号查询品牌信息
    @Override
    public PmsBrand getMsgById(Long id) {
        return pmsBrandMapper.selectById(id);
    }

    //批量更新厂家制造商状态
    @Override
    public void updateFactoryStatus(Integer factoryStatus, List<Long> idList) {
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsBrand::getId,idList);
        List<PmsBrand> brandList = pmsBrandMapper.selectList(queryWrapper);
        for (PmsBrand brand : brandList) {
            brand.setFactoryStatus(factoryStatus);
            LambdaQueryWrapper<PmsBrand> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsBrand::getId,brand.getId());
            pmsBrandMapper.update(brand,queryWrapper1);
        }
    }

    //批量更新显示状态
    @Override
    public void updateShowStatus(Integer showStatus, List<Long> idList) {
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsBrand::getId,idList);
        List<PmsBrand> brandList = pmsBrandMapper.selectList(queryWrapper);
        for (PmsBrand brand : brandList) {
            brand.setShowStatus(showStatus);
            LambdaQueryWrapper<PmsBrand> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PmsBrand::getId,brand.getId());
            pmsBrandMapper.update(brand,queryWrapper1);
        }
    }

    //更新品牌
    @Override
    public Boolean updateById(Long id, PmsBrand pmsBrandParam) {
        PmsBrand brand = new PmsBrand();
        brand.setId(id);
        BeanUtil.copyProperties(pmsBrandParam,brand,"id");
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsBrand::getId,id);
        int count = pmsBrandMapper.update(brand, queryWrapper);
        return count != 0;
    }



    //模糊查询得到的品牌
    @Override
    public IPage<PmsBrand> findABrandsByKeyWord(String keyword, Integer pageNum, Integer pageSize) {
        //创建分页对象，设置分页参数
        //注意：使用分页，需要配置分页插件
        IPage<PmsBrand> pg = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(PmsBrand::getSort);
        queryWrapper.like(null!= keyword,PmsBrand::getName,keyword);
        pmsBrandMapper.selectPage(pg,queryWrapper);
        return pg;
    }


}
