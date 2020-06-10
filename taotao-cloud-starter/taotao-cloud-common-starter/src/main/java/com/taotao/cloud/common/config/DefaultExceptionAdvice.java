package com.taotao.cloud.common.config;

import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.*;
import com.taotao.cloud.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 异常通用处理
 *
 * @author dengtao
 * @date 2020/5/2 09:12
 */
@Slf4j
public class DefaultExceptionAdvice {

    @ExceptionHandler({LockException.class})
    public Result<String> lockException(NativeWebRequest request, LockException e) {
        printException(request, e);
        return Result.failed(ResultEnum.LOCK_EXCEPTION.getCode(), e.getMessage());
    }

    @ExceptionHandler({IdempotencyException.class})
    public Result<String> idempotencyException(NativeWebRequest request, IdempotencyException e) {
        printException(request, e);
        return Result.failed(ResultEnum.IDEMPOTENCY_EXCEPTION.getCode(), e.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public Result<String> businessException(NativeWebRequest request, BusinessException e) {
        printException(request, e);
        return Result.failed(ResultEnum.ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler({BaseException.class})
    public Result<String> badBaseException(NativeWebRequest request, BaseException e) {
        printException(request, e);
        return Result.failed(ResultEnum.ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(NativeWebRequest request, Exception e) {
        printException(request, e);
        return Result.failed(ResultEnum.ERROR.getCode(), e.getMessage());
    }


    // ----------------------------------------------------------------


    @ExceptionHandler({IllegalArgumentException.class})
    public Result<String> badRequestException(NativeWebRequest request, IllegalArgumentException e) {
        printException(request, e);
        return Result.of(e.getMessage(), HttpStatus.BAD_REQUEST.value(), "参数解析失败");
    }

//    /**
//     * AccessDeniedException异常处理返回json
//     * 返回状态码:403
//     */
//    @ExceptionHandler({AccessDeniedException.class})
//    public Result<String> badMethodExpressException(NativeWebRequest request, AccessDeniedException e) {
//        printException(request, e);
//        return Result.of(e.getMessage(), HttpStatus.FORBIDDEN.value(), "没有权限请求当前方法");
//    }


    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     */
    @ExceptionHandler({MessageException.class})
    public Result<String> badMessageException(NativeWebRequest request, MessageException e) {
        printException(request, e);
        return Result.of(e.getMessage(), HttpStatus.FORBIDDEN.value(), "发送消息异常");
    }

//    /**
//     * AccessDeniedException异常处理返回json
//     * 返回状态码:403
//     */
//    @ExceptionHandler({UsernameNotFoundException.class})
//    public Result<String> badUsernameNotFoundException(NativeWebRequest request, UsernameNotFoundException e) {
//        printException(request, e);
//        return Result.of(e.getMessage(), HttpStatus.FORBIDDEN.value(), "错误");
//    }

    /**
     * 返回状态码:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result<String> handleHttpRequestMethodNotSupportedException(NativeWebRequest request, HttpRequestMethodNotSupportedException e) {
        printException(request, e);
        return Result.of(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持当前请求方法");
    }

    /**
     * 返回状态码:415
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Result<String> handleHttpMediaTypeNotSupportedException(NativeWebRequest request, HttpMediaTypeNotSupportedException e) {
        printException(request, e);
        return Result.of(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持当前媒体类型");
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ExceptionHandler({SQLException.class})
    public Result<String> handleSQLException(NativeWebRequest request, SQLException e) {
        printException(request, e);
        return Result.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SQLException异常");
    }


    private void printException(NativeWebRequest request, Exception e) {
        HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
        if (Objects.nonNull(nativeRequest)) {
            String uri = nativeRequest.getRequestURI();
            String queryString = nativeRequest.getQueryString();
            log.error("请求路径: " + uri + "   请求参数: " + queryString, e);
        } else {
            log.error("", e);
        }
    }
}
