package com.taotao.cloud.message.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 短信平台配置
 *
 * @author dengtao
 * @date 2020/4/30 10:19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "taotao.cloud.message.sms")
public class SmsProperties {

    private boolean enabled = true;

    /**
     * 阿里云短信
     */
    AliYunSmsProperties aliyun = new AliYunSmsProperties();

    /**
     * 秒滴云短信
     */
    MiaoDiYunSmsProperties miaodiyun = new MiaoDiYunSmsProperties();


}
