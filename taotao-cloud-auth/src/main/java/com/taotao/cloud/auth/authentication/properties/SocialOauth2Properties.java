package com.taotao.cloud.auth.authentication.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 第三方认证Properties
 *
 * @author dengtao
 * @date 2020/4/29 20:20
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.social")
public class SocialOauth2Properties {

    private SocialSecurityProperties security;
}
