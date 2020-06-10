package com.taotao.cloud.taotaoclouddemogateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 动态路由配置
 *
 * @author dengtao
 * @date 2020/5/2 11:15
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.dynamic.route")
public class DynamicRouteProperties {

    /**
     * 是否开启
     */
    private Boolean enabled = false;

    /**
     * 类型
     */
    private String type = "nacos";

}
