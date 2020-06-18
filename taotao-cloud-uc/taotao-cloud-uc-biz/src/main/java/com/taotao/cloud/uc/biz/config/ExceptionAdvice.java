package com.taotao.cloud.uc.biz.config;

import com.taotao.cloud.common.config.DefaultExceptionAdvice;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.model.Result;
import feign.codec.DecodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionAdvice
 *
 * @author dengtao
 * @date 2020/5/2 11:17
*/
@RestControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     */
    @ExceptionHandler({DecodeException.class})
    public Result<String> badDecodeException(BaseException e) {
        return Result.of(e.getMessage(), HttpStatus.FORBIDDEN.value(), "没有权限请求当前方法");
    }
}
