package com.taotao.cloud.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * token配置
 *
 * @author dengtao
 * @date 2020/4/30 08:52
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.oauth2.token.store")
public class TokenStoreProperties {
    /**
     * token 存储地址
     */
    private String type = "redis";
}
