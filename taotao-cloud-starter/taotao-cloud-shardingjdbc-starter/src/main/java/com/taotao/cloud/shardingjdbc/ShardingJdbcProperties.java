package com.taotao.cloud.shardingjdbc;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * ShardingJdbcProperties
 *
 * @author dengtao
 * @date 2020/6/22 17:30
 */
@ConfigurationProperties(value = "taotao.cloud.shardingsphere")
public class ShardingJdbcProperties {

    private boolean enabled = false;
}
