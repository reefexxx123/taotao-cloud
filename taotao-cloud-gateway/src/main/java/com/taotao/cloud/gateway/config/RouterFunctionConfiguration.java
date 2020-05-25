package com.taotao.cloud.gateway.config;

import com.taotao.cloud.gateway.handler.HystrixFallbackHandler;
import com.taotao.cloud.gateway.handler.ImageCodeHandler;
import com.taotao.cloud.gateway.properties.CustomGatewayProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 路由配置信息 特殊请求直接在此处理，不进行路由转发
 *
 * @author dengtao
 * @date 2020/4/29 22:11
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private final HystrixFallbackHandler hystrixFallbackHandler;
    private final ImageCodeHandler imageCodeWebHandler;
    private final CustomGatewayProperties customGatewayProperties;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET(customGatewayProperties.getBaseUri() + "/code")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeWebHandler);

    }
}
