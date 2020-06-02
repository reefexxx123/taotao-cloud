package com.taotao.cloud.social.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * QqConfig
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "taotao.cloud.social.security.qq")
public class QqProperties extends BaseSocialProperties {

    private String redirectUri;
}
