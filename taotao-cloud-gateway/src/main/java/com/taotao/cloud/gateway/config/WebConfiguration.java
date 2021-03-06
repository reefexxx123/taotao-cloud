package com.taotao.cloud.gateway.config;

import com.taotao.cloud.gateway.properties.CustomGatewayProperties;
import com.taotao.cloud.gateway.properties.DynamicRouteProperties;
import com.taotao.cloud.gateway.properties.TraceProperties;
import com.taotao.cloud.gateway.swagger.SwaggerAggProperties;
import com.taotao.cloud.gateway.swagger.SwaggerProvider;
import com.taotao.cloud.log.utils.LogUtil;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

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

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}

