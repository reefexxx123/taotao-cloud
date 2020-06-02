package com.taotao.cloud.auth.store;

import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.auth.properties.TokenStoreProperties;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
@ConditionalOnProperty(prefix = "taotao.cloud.oauth2.token.store", name = "type", havingValue = "redis", matchIfMissing = true)
@EnableConfigurationProperties({TokenStoreProperties.class})
public class AuthRedisTokenStore implements InitializingBean {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public TokenStore tokenStore() {
        return new CustomRedisTokenStore(connectionFactory, securityProperties);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(AuthRedisTokenStore.class, StarterNameConstant.TAOTAO_CLOUD_AUTH_STARTER, "redis认证token已启动");
    }
}
