package com.taotao.cloud.log.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 日志链路追踪配置
 *
 * @author dengtao
 * @date 2020/5/2 11:15
*/
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.log.trace")
public class TraceProperties {

    /**
     * 是否开启日志链路追踪
     */
    private Boolean enable = false;
}
