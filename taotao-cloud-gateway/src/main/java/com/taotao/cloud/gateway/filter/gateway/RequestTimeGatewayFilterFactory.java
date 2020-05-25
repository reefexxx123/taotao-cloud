/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.gateway.filter.gateway
 * Date: 2020/5/2 18:37
 * Author: dengtao
 */
package com.taotao.cloud.gateway.filter.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/2 18:37
 */
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {
    private static final Logger log = LoggerFactory.getLogger(RequestTimeGatewayFilterFactory.class);
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(COUNT_START_TIME);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");
                            sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            log.info(sb.toString());
                        }
                    })
            );
        };
    }

    public static class Config {
        /**
         * 控制是否开启统计
         */
        private boolean enabled;

        public Config() {
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
