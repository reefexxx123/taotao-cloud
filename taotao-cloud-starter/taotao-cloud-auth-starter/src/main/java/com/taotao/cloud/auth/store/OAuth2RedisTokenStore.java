package com.taotao.cloud.auth.store;

import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.auth.properties.TokenStoreProperties;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器使用Redis存取令牌
 *
 * @author dengtao
 * @date 2020/4/30 09:07
 */
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

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(OAuth2RedisTokenStore.class, StarterNameConstant.TAOTAO_CLOUD_AUTH_STARTER, "redis认证token已启动, Security配置已启动");
    }
}