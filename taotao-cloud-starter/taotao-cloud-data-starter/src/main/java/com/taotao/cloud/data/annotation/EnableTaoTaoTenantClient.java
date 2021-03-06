package com.taotao.cloud.data.annotation;

import com.taotao.cloud.data.config.TenantConfig;
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
@Import({TenantConfig.class})
public @interface EnableTaoTaoTenantClient {

}
