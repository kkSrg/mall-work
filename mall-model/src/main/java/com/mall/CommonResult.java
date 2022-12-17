package com.mall;

import com.mall.pojo.OmsOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CommonResult<T> implements Serializable {

    private Integer code; //编码：0成功，1和其它数字为失败

    private String message; //错误信息

    private T data; //数据


    public static <T> CommonResult<T> success(T object) {
        CommonResult<T> r = new CommonResult<T>();
        r.data = object;
        r.code = 200;
        r.message="操作成功";
        return r;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult r = new CommonResult();
        r.message = msg;
        r.code = 1;
        return r;
    }

}
