package com.taotao.cloud.auth.authentication.social;


import com.taotao.cloud.auth.authentication.properties.SocialOauth2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * SocialConfig
 *
 * @author dengtao
 * @date 2020/4/29 20:34
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SocialOauth2Properties socialOauth2Properties;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        TaotaoJdbcUsersConnectionRepository repository = new TaotaoJdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("social_");
        return repository;
    }

    @Bean
    public SpringSocialConfigurer mySpringSocialSecurityConfig() {
        String filterProcessesUrl = socialOauth2Properties.getSecurity().getFilterProcessesUrl();
        TaotaoSpringSocialConfigurer configurer = new TaotaoSpringSocialConfigurer(filterProcessesUrl);
        //1、认证失败跳转注册页面
        // 跳转到signUp controller，从session中获取用户信息并通过生成的uuid保存到redis里面，然后跳转bind页面
        // 前端绑定后发送用户信息到后台bind controller，
        // 1）保存到自己系统用户；
        // 2）保存一份userconnection表数据，Spring Social通过这里面表数据进行判断是否绑定
        configurer.signupUrl("/signUp");
        //2、认证成功跳转后处理器，跳转带token的成功页面
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);

        return configurer;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }


    /**
     * 从Session中获取社交账号信息
     *
     * @return org.springframework.social.connect.web.ProviderSignInUtils
     * @author dengtao
     * @date 2020/4/29 20:34
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator)) {};
    }
}
