package com.taotao.cloud.message.properties;

import lombok.Data;

/**
 * 秒滴云短信
 *
 * @author dengtao
 * @date 2020/4/30 10:19
 */
@Data
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
