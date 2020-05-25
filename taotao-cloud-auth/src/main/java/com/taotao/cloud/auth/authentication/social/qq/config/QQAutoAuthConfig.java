package com.taotao.cloud.auth.authentication.social.qq.config;

import com.taotao.cloud.auth.authentication.properties.QQProperties;
import com.taotao.cloud.auth.authentication.properties.SocialOauth2Properties;
import com.taotao.cloud.auth.authentication.social.AbstractSocialAutoConfigurerAdapter;
import com.taotao.cloud.auth.authentication.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * 把QQ登录的自定义配置传给ConnectionFactory
 *
 * @author dengtao
 * @date 2020/4/29 21:03
 */
@Configuration
@EnableSocial
@ConditionalOnProperty(prefix = "taotao.cloud.social.security.qq", name = "app-id")
public class QQAutoAuthConfig extends AbstractSocialAutoConfigurerAdapter {

    @Autowired
    private SocialOauth2Properties socialOauth2Properties;


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = socialOauth2Properties.getSecurity().getQq();
        String providerId = qqProperties.getProviderId();
        String appId = qqProperties.getAppId();
        String appSecret = qqProperties.getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }

}
