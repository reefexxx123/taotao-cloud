package com.taotao.cloud.social.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * GitHubConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "taotao.cloud.social.security.github")
public class GitHubConfig extends SocialProperties {

    private String redirectUri;
}
