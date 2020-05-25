package com.taotao.cloud.elk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: dengtao
 * @version : 2019-05-27 14:30
 **/
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "taotao.cloud.elk.web.enabled", havingValue = "true")
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
    public WebControllerAspect webControllerAspect(){
        return new WebControllerAspect();
    }


}
