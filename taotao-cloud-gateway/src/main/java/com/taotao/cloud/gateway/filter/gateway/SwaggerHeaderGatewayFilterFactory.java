package com.taotao.cloud.gateway.filter.gateway;

import com.taotao.cloud.gateway.swagger.SwaggerAggProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

/**
 * SwaggerHeaderFilter
 *
 * @author dengtao
 * @date 2020/4/29 22:13
 */
@Slf4j
@Component
public class SwaggerHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Autowired
    private SwaggerAggProperties swaggerAggProperties;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (!StringUtils.endsWithIgnoreCase(path, swaggerAggProperties.getApiDocsPath())) {
                return chain.filter(exchange);
            }
            String basePath = path.substring(0, path.lastIndexOf(swaggerAggProperties.getApiDocsPath()));
            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }
}
