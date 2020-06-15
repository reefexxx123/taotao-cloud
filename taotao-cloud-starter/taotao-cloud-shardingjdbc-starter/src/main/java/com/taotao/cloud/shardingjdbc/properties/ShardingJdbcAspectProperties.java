package com.taotao.cloud.shardingjdbc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(value = "taotao.cloud.shardingjdbc.aspect.enabled")
public class ShardingJdbcAspectProperties {
    private boolean enabled = false;
}
