package com.mall;

import com.mall.pojo.OmsOrder;
import com.sun.xml.internal.bind.v2.schemagen.episode.Package;
import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer pageNum;  //当前页码
    private Integer pageSize;  //页大小
    private Integer totalPage;  //总页数
    private Integer total;  //总记录数
    private List<T> list;  //数据列表
}