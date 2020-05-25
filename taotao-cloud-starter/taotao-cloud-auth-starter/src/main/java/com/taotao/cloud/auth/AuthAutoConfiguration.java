package com.taotao.cloud.auth;

import com.taotao.cloud.auth.config.DefaultSecurityHandlerConfig;
import com.taotao.cloud.auth.config.ResourceServerConfig;
import com.taotao.cloud.auth.config.SecurityPropertiesConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * AuthAutoConfiguration
 *
 * @author dengtao
 * @date 2020/5/3 10:46
 */
@Configuration
@ConditionalOnProperty(prefix = "taotao.cloud.oauth2.security", name = "enabled", matchIfMissing = true)
@Import({SecurityPropertiesConfig.class, ResourceServerConfig.class, DefaultSecurityHandlerConfig.class})
public class AuthAutoConfiguration {

}
