package com.mall;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class CommonResult<T> implements Serializable {

    private Integer code; //编码：0成功，1和其它数字为失败

    private String message; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> CommonResult<T> success(T object) {
        CommonResult<T> r = new CommonResult<T>();
        r.data = object;
        r.code = 0;
        return r;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult r = new CommonResult();
        r.message = msg;
        r.code = 1;
        return r;
    }

    public CommonResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}