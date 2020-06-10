package com.taotao.cloud.taotaoclouddemogateway.config;

import com.taotao.cloud.taotaoclouddemogateway.properties.CustomGatewayProperties;
import com.taotao.cloud.taotaoclouddemogateway.properties.DynamicRouteProperties;
import com.taotao.cloud.taotaoclouddemogateway.properties.TraceProperties;
import com.taotao.cloud.taotaoclouddemogateway.swagger.SwaggerAggProperties;
import com.taotao.cloud.taotaoclouddemogateway.swagger.SwaggerProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 全局配置
 *
 * @author dengtao
 * @date 2020/4/29 22:13
 */
@Configuration
@EnableConfigurationProperties({TraceProperties.class, DynamicRouteProperties.class,
        CustomGatewayProperties.class, SwaggerAggProperties.class})
@Import(SwaggerProvider.class)
@Order
public class WebConfiguration {

    @Bean(name = "remoteAddrKeyResolver")
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return chain.filter(exchange);
            }
        };
    }
}

