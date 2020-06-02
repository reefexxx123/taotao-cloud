package com.taotao.cloud.shardingjdbc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 15:35
 **/
@ConfigurationProperties(value = "taotao.cloud.shardingjdbc.aspect.enabled")
public class ShardingJdbcAspectProperties {
    private boolean enabled = false;
}
