package com.taotao.cloud.elk.configuration;

import com.taotao.cloud.elk.ElkWebInterceptor;
import com.taotao.cloud.elk.WebControllerAspect;
import com.taotao.cloud.elk.properties.ElkHealthLogStatisticProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ElkWebConfiguration
 *
 * @version : 2019-05-27 14:30
 * @author: dengtao
 **/
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "taotao.cloud.elk.web", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({ElkHealthLogStatisticProperties.class})
public class ElkWebConfiguration implements WebMvcConfigurer {

    @Bean
    public ElkWebInterceptor elkWebInterceptor() {
        return new ElkWebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(elkWebInterceptor());
    }

    @Bean
    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
    @ConditionalOnProperty(prefix = "taotao.cloud.elk.log.statistic", name = "enabled", havingValue = "true")
    public WebControllerAspect webControllerAspect() {
        return new WebControllerAspect();
    }

}
