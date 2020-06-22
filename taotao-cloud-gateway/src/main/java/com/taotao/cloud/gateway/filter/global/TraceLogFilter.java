package com.taotao.cloud.gateway.filter.global;

import cn.hutool.core.util.IdUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.gateway.properties.TraceProperties;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 生成日志链路追踪id，并传入header中
 *
 * @author dengtao
 * @date 2020/4/29 22:13
 */
@Component
public class TraceLogFilter implements GlobalFilter, Ordered {

    private final TraceProperties traceProperties;

    public TraceLogFilter(TraceProperties traceProperties){
        this.traceProperties = traceProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (traceProperties.getEnabled()) {
            //链路追踪id
            String traceId = IdUtil.fastSimpleUUID();
            MDC.clear();
            MDC.put(CommonConstant.LOG_TRACE_ID, traceId);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> h.add(CommonConstant.TRACE_ID_HEADER, traceId))
                    .build();

            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

