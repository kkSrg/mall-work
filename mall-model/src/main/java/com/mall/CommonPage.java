package com.mall;

import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer pageNum;  //当前页码
    private Integer pageSize;  //页大小
    /**
     * 总页数
     */
    private Integer totalPage;
    private Integer total;  //总记录数
    private List<T> list;  //数据列表
}