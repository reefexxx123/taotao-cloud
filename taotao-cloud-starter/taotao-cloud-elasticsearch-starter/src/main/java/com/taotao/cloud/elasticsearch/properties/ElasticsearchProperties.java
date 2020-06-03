package com.taotao.cloud.elasticsearch.properties;

import lombok.Data;
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
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.elasticsearch")
public class ElasticsearchProperties {

    /**
     * Elasticsearch 总开关
     */
    private boolean enabled = false;
}
