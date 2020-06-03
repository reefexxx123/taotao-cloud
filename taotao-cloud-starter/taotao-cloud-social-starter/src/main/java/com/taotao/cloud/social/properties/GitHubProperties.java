package com.taotao.cloud.social.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * GitHubConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.social.security.github")
public class GitHubProperties extends BaseSocialProperties {

    private String redirectUri;
}
