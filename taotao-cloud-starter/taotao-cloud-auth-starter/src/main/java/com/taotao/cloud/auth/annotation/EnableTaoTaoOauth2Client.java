package com.taotao.cloud.auth.annotation;

import com.taotao.cloud.auth.config.ResourceServerConfig;
import com.taotao.cloud.auth.config.SecurityHandlerConfig;
import com.taotao.cloud.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启认证服务器和客户端
 *
 * @author dengtao
 * @date 2020/5/3 07:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ResourceServerConfig.class, SecurityHandlerConfig.class})
public @interface EnableTaoTaoOauth2Client {

}
