package com.taotao.cloud.common.model;

import com.taotao.cloud.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回实体类
 *
 * @author dengtao
 * @date 2020/4/29 15:15
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3685249101751401211L;

    private static final String SUCCESS = "请求成功";
    private static final String ERROR = "请求失败";

    private T data;
    private Integer code;
    private String message;

    public Result() {}

    public Result(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> succeed(T data, String msg) {
        return of(data, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T data) {
        return of(data, ResultEnum.SUCCESS.getCode(), SUCCESS);
    }

    public static <T> Result<T> of(T data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }

    public static <T> Result<T> failed(Integer code, String msg) {
        return of(null, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model) {
        return of(model, ResultEnum.ERROR.getCode(), ERROR);
    }
}
