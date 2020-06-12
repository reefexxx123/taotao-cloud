package com.taotao.cloud.auth.store;

import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.auth.properties.TokenStoreProperties;
import com.taotao.cloud.common.constant.StarterNameConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.context.request.RequestContextListener;

/**
 * 认证服务器使用Redis存取令牌
 *
 * @author dengtao
 * @date 2020/4/30 09:07
 */
@Slf4j
@AllArgsConstructor
@EnableConfigurationProperties({TokenStoreProperties.class, SecurityProperties.class})
@ConditionalOnProperty(prefix = "taotao.cloud.oauth2.token.store", name = "type", havingValue = "redis")
public class OAuth2RedisTokenStore implements InitializingBean {

    private final RedisConnectionFactory connectionFactory;
    private final SecurityProperties securityProperties;

    @Bean
    public TokenStore tokenStore() {
        return new CustomRedisTokenStore(connectionFactory, securityProperties);
    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TAOTAO CLOUD][" + StarterNameConstant.TAOTAO_CLOUD_AUTH_STARTER + "]" + "redis认证token已启动, Security配置已启动");
    }
}
