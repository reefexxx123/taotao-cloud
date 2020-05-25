package com.taotao.cloud.auth.authentication.social;

import org.omg.CORBA.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 不想引入1.5版本的springboot的话只能自己按照源码重写
 *
 * @author dengtao
 * @date 2020/4/29 20:37
 */
public abstract class AbstractSocialAutoConfigurerAdapter extends SocialConfigurerAdapter {
    public AbstractSocialAutoConfigurerAdapter() {
    }

    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }

    protected abstract ConnectionFactory<?> createConnectionFactory();
}

