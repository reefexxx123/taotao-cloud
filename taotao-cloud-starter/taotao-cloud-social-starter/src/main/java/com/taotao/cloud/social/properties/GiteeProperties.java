package com.taotao.cloud.social.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * GiteeConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.social.security.gitee")
public class GiteeProperties extends BaseSocialProperties {

    private String redirectUri;
}
