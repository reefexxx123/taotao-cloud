package com.taotao.cloud.ribbon.annotation;

import com.taotao.cloud.ribbon.config.FeignHttpInterceptorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启feign拦截器传递数据给下游服务，包含基础数据和http的相关数据
 *
 * @author dengtao
 * @date 2020/4/5 13:40
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FeignHttpInterceptorConfig.class})
public @interface EnableTaoTaoFeignInterceptor {

}
