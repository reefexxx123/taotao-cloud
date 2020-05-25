package com.taotao.cloud.social.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * QqConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "taotao.cloud.social.security.qq")
public class QqConfig extends SocialProperties {

    private String redirectUri;
}
