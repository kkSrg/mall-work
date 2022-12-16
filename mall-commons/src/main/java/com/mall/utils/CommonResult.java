package com.mall.utils;

import java.io.Serializable;
import java.util.Map;

public class CommonResult<T> implements Serializable {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String message; //错误信息

    private T data; //数据

    private Map map;

    public static <T> CommonResult<T> success(T object) {
        CommonResult<T> r = new CommonResult<T>();
        r.data = object;
        r.message="操作成功";
        r.code = 200;
        return r;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult r = new CommonResult();
        r.message = msg;
        r.code = 0;
        return r;
    }

    public CommonResult<T> add(String key, Object value) {
        map.put(key, value);
        return this;
    }
}
