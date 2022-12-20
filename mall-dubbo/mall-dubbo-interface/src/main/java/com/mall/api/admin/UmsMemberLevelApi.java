package com.mall.api.admin;

import com.mall.pojo.UmsMemberLevel;

import java.util.List;

public interface UmsMemberLevelApi {
    List<UmsMemberLevel> list(Integer defaultStatus);
}
