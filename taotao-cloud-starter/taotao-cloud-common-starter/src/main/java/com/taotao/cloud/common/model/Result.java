package com.taotao.cloud.common.model;

import com.taotao.cloud.common.enums.ResultEnum;
import lombok.Data;

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

//    @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue})
    private T data;
//    @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue})
    private Integer code;
//    @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue})
    private String message;

    public Result() {
    }

    public Result(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> succeed(T data, String msg) {
        return of(data, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T data) {
        return of(data, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> succeed(T data, ResultEnum resultEnum) {
        return of(data, resultEnum.getCode(), resultEnum.getMessage());
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
        return of(model, ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

    public static <T> Result<T> failed(T model, ResultEnum resultEnum) {
        return of(model, resultEnum.getCode(), resultEnum.getMessage());
    }

    public static <T> Result<T> failed() {
        return of(null, ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }
}
