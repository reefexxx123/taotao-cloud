package com.taotao.cloud.shardingjdbc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(value = "taotao.cloud.shardingjdbc.enabled")
public class ShardingJdbcProperties {
    private boolean enabled = false;
}
