package com.taotao.cloud.social.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * GiteeConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:11
 */
@Data
@ConfigurationProperties(prefix = "taotao.cloud.social.security.gitee")
public class GiteeConfig extends SocialProperties {

    private String redirectUri;
}
