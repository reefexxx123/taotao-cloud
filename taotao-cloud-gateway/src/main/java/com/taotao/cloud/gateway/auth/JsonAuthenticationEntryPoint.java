package com.taotao.cloud.gateway.auth;

import com.taotao.cloud.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 401未授权异常处理
 *
 * @author dengtao
 * @date 2020/4/29 22:10
 */
@Slf4j
public class JsonAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private static final String UN_AUTHORIZED = "用户未授权";

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return ResponseUtil.failed(exchange, HttpStatus.UNAUTHORIZED.value(), UN_AUTHORIZED);
    }
}
