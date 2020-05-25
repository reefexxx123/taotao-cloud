package com.taotao.cloud.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author dengtao
 * @date 2020/5/13 10:56
 */
@Data
public class BaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 6610083281801529147L;

    private String message;

    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public BaseException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

}
