package com.taotao.cloud.elasticsearch.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 索引配置
 *
 * @author dengtao
 * @date 2020/5/3 08:00
*/
@Setter
@Getter
@ConfigurationProperties(prefix = "taotao.cloud.elasticsearch.indices")
@RefreshScope
public class IndexProperties {
    /**
     * 配置过滤的索引名：默认只显示这些索引
     */
    private String show;
}
