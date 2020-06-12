package com.taotao.cloud.message;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.message.config.AliyunSmsConfiguration;
import com.taotao.cloud.message.properties.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * MessageConfiguration
 *
 * @author dengtao
 * @date 2020/5/3 10:46
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "taotao.cloud.message.sms", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({SmsProperties.class})
@Import({AliyunSmsConfiguration.class})
public class MessageAutoConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TAOTAO CLOUD][" + StarterNameConstant.TAOTAO_CLOUD_MESSAGE_STARTER + "]" + "消息模块已启动");
    }
}
