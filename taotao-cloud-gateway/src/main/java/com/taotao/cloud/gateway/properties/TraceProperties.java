package com.taotao.cloud.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 日志链路追踪配置
 *
 * @author dengtao
 * @date 2020/5/2 11:15
*/
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "taotao.cloud.log.trace")
public class TraceProperties {

    /**
     * 是否开启日志链路追踪d
     */
    private Boolean enabled = false;
}
