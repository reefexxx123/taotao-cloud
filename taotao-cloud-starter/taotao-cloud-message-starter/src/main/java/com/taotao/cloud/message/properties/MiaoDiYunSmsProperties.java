package com.taotao.cloud.message.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 秒滴云短信
 *
 * @author dengtao
 * @date 2020/4/30 10:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@ConfigurationProperties(prefix = "taotao.cloud.message.sms.miaodiyun")
public class MiaoDiYunSmsProperties extends BaseSmsProperties {

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 短信类型
     */
    private String type = "miaodiyun";

    /**
     * baseUrl
     */
    private String baseUrl = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
}
