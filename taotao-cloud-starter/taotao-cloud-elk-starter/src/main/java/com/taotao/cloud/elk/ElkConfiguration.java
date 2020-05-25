package com.taotao.cloud.elk;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.encoder.Encoder;
import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.utils.LogUtils;
import com.taotao.cloud.elk.properties.ElkControllerAspectProperties;
import com.taotao.cloud.elk.properties.ElkHealthLogStatisticProperties;
import com.taotao.cloud.elk.properties.ElkProperties;
import lombok.var;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @version : 2019-05-27 14:30
 * @author: dengtao
 **/
@EnableConfigurationProperties({ElkProperties.class, ElkControllerAspectProperties.class, ElkHealthLogStatisticProperties.class})
@ConditionalOnProperty(name = "taotao.cloud.elk.enabled", havingValue = "true")
public class ElkConfiguration implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(ElkConfiguration.class, ProjectNameConstant.TAOTAO_CLOUD_ELK_STARTER, "elk模块已启动");
    }

    @Autowired
    private ElkProperties elkProperties;

    @Autowired(required = false)
    private LogStatisticsFilter logStatisticsFilter;

    private Encoder<ILoggingEvent> createEncoder() {
        LogstashEncoder encoder = new LogstashEncoder();
        var appName = elkProperties.getAppName();
        if (elkProperties.getAppName().length() == 0) {
            appName = elkProperties.getSpringAppName();
        }
        if (appName.length() == 0) {
            throw new IllegalArgumentException("缺少appName配置");
        }
        encoder.setCustomFields("{\"appname\":\"" + appName + "\",\"appindex\":\"applog\"}");
        encoder.setEncoding("UTF-8");
        return encoder;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public LogstashTcpSocketAppender logstashTcpSocketAppender() {
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        var destinations = elkProperties.getDestinations();
        if (elkProperties.getDestinations() == null || elkProperties.getDestinations().length == 0) {
            throw new IllegalArgumentException("未设置elk地址");
//            destinations = new String[]{
//                    PropertyUtils.getPropertyCache(CoreEnvironmentEnum.ELK_DEV.getServerkey(), "")};
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
    @ConditionalOnProperty(name = "taotao.cloud.elk.health.log.statistic.enabled", havingValue = "true")
    LogStatisticsFilter getLogStatisticsFilter() {
        return new LogStatisticsFilter();
    }
}
