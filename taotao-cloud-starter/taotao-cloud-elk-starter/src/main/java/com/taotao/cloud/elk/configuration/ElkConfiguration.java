package com.taotao.cloud.elk.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.encoder.Encoder;
import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.elk.LogStatisticsFilter;
import com.taotao.cloud.elk.properties.ElkControllerAspectProperties;
import com.taotao.cloud.elk.properties.ElkProperties;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * ElkConfiguration
 *
 * @author dengtao
 * @date 2020/6/3 10:43
 */
@Slf4j
@ConditionalOnProperty(prefix = "taotao.cloud.elk", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({ElkProperties.class, ElkControllerAspectProperties.class})
public class ElkConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TAOTAO CLOUD][" + StarterNameConstant.TAOTAO_CLOUD_ELK_STARTER + "]" + "elk模块已启动");
    }

    @Resource
    private ElkProperties elkProperties;

    @Resource
    private LogStatisticsFilter logStatisticsFilter;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public LogstashTcpSocketAppender logstashTcpSocketAppender() {
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        String[] destinations = elkProperties.getDestinations();
        if (elkProperties.getDestinations() == null || elkProperties.getDestinations().length == 0) {
            throw new BaseException("未设置elk地址");
        }

        for (String destination : destinations) {
            appender.addDestination(destination);
        }
        appender.setEncoder(createEncoder());

        ILoggerFactory factory = LoggerFactory.getILoggerFactory();
        if (factory instanceof LoggerContext) {
            LoggerContext context = ((LoggerContext) factory);
            appender.setContext(context);
            context.getLogger("ROOT").addAppender(appender);
        }

        if (logStatisticsFilter != null) {
            //增加错误日志统计拦截
            appender.addFilter(logStatisticsFilter);
        }

        return appender;
    }

    @Bean
    @ConditionalOnProperty(prefix = "taotao.cloud.elk.log.statistic", name = "enabled", havingValue = "true")
    LogStatisticsFilter getLogStatisticsFilter() {
        return new LogStatisticsFilter();
    }

    private Encoder<ILoggingEvent> createEncoder() {
        LogstashEncoder encoder = new LogstashEncoder();
        String appName = elkProperties.getAppName();
        if (StrUtil.isBlank(appName)) {
            appName = elkProperties.getSpringAppName();
        }

        if (StrUtil.isBlank(appName)) {
            throw new BaseException("缺少appName配置");
        }
        encoder.setCustomFields("{\"appname\":\"" + appName + "\",\"appindex\":\"applog\"}");
        encoder.setEncoding("UTF-8");

        return encoder;
    }
}
