package com.taotao.cloud.gateway.handler;

import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 403拒绝访问异常处理，转换为JSON
 *
 * @author dengtao
 * @date 2020/4/29 22:10
 */
@Slf4j
public class JsonAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        return ResponseUtil.responseFailed(exchange, ResultEnum.ERROR.getCode(), "无效的token拒绝访问");
    }

}
