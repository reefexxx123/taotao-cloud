//package com.taotao.cloud.taotaoclouddemogateway.config;
//
//import com.taotao.cloud.gateway.handler.HystrixFallbackHandler;
//import com.taotao.cloud.gateway.handler.ImageCodeHandler;
//import com.taotao.cloud.gateway.properties.CustomGatewayProperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
///**
// * 特殊路由配置信息
// *
// * @author dengtao
// * @date 2020/4/29 22:11
// */
//@Slf4j
//@Configuration
//@AllArgsConstructor
//public class RouterFunctionConfiguration {
//
//    private static final String FALLBACK = "/fallback";
//    private static final String CODE = "/code";
//
//    private final HystrixFallbackHandler hystrixFallbackHandler;
//    private final ImageCodeHandler imageCodeWebHandler;
//    private final CustomGatewayProperties customGatewayProperties;
//
//    @Bean
//    public RouterFunction<ServerResponse> routerFunction() {
//        return RouterFunctions.route(
//                RequestPredicates.path(FALLBACK)
//                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
//                .andRoute(RequestPredicates.GET(customGatewayProperties.getBaseUri() + CODE)
//                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeWebHandler);
//
//    }
//}
