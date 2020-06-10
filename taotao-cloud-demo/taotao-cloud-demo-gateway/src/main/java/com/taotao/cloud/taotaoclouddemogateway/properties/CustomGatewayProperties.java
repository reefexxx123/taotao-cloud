package com.taotao.cloud.taotaoclouddemogateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 网关配置
 *
 * @author dengtao
 * @date 2020/5/2 11:15
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.gateway")
public class CustomGatewayProperties {

    /**
     * 网关基础路由前缀
     */
    private String prefix = "/api";

    /**
     * 网关基础路由版本
     */
    private String version = "";

    /**
     * 网关基础路由uri
     */
    private String baseUri = "";
}
