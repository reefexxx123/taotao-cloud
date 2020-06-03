package com.taotao.cloud.ribbon.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * RibbonProperties 配置
 *
 * @author dengtao
 * @date 2017/11/17
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.ribbon.isolation")
public class RibbonIsolationProperties {

    /**
     * 是否开启ribbon自定义隔离规则
     */
    private boolean enabled = true;
}
