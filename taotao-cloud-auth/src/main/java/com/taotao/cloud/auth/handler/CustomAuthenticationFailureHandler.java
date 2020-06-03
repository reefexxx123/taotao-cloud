package com.taotao.cloud.auth.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cloud.auth.exception.ValidateCodeException;
import com.taotao.cloud.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author dengtao
 * @date 2020/5/13 16:08
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String message;
        if (exception instanceof ValidateCodeException) {
            message = exception.getMessage();
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或者密码错误";
        } else {
            message = "用户认证失败";
        }
        ResponseUtil.writeResponse(response, message, HttpStatus.UNAUTHORIZED.value());
    }
}


