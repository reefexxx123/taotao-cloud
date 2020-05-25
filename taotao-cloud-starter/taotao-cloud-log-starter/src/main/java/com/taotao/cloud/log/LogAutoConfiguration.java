package com.taotao.cloud.log;

import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.utils.LogUtils;
import com.taotao.cloud.log.aspect.SysLogAspect;
import com.taotao.cloud.log.listener.SysLogListener;
import com.taotao.cloud.log.properties.SysLogProperties;
import com.taotao.cloud.log.properties.TraceProperties;
import com.taotao.cloud.log.util.LogUtil;
import feign.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 当web项目引入此依赖时，自动配置对应的内容 初始化log的事件监听与切面配置
 *
 * @author dengtao
 * @date 2020/4/30 10:21
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({TraceProperties.class, SysLogProperties.class})
@EnableFeignClients({"com.taotao.cloud.log.feign"})
public class LogAutoConfiguration implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(LogAutoConfiguration.class, ProjectNameConstant.TAOTAO_CLOUD_LOG_STARTER, "日志模块已启动");
    }

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener();
    }

    @Bean
    public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
        return new SysLogAspect(publisher);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}

