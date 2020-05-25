package com.taotao.cloud.ribbon.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RibbonProperties 配置
 *
 * @author dengtao
 * @date 2017/11/17
 */
@Data
@ConfigurationProperties(prefix = "taotao.cloud.ribbon.isolation")
public class RibbonProperties {
    /**
     * 是否开启ribbon自定义隔离规则
     */
    private boolean enabled = true;
}
