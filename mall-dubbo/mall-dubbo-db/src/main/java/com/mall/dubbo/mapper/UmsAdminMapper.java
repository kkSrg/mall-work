package com.mall.dubbo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户业务
 *
 * @author 吴一飞
 * @date 2022/12/15 22:19
 */

@Mapper
public interface UmsAdminMapper extends BaseMapper<Admin> {
}
