package com.taotao.cloud.message.config;

import com.taotao.cloud.message.properties.AliYunSmsProperties;
import com.taotao.cloud.message.properties.SmsProperties;
import com.taotao.cloud.message.service.IAliyunSmsMessageService;
import com.taotao.cloud.message.service.impl.AliyunSmsMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉配置类
 *
 * @author dengtao
 * @date 2020/5/3 10:43
 */
@Configuration
@ConditionalOnProperty(prefix = "taotao.cloud.message.sms.aliyun", name = "enabled", matchIfMissing = true)
public class AliyunSmsConfiguration {

    @Autowired
    private SmsProperties smsProperties;

    @Bean
    public IAliyunSmsMessageService getDingding() {
        return new AliyunSmsMessageServiceImpl(smsProperties);
    }
}
