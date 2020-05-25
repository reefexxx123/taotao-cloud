package com.taotao.cloud.message.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 阿里短信配置
 *
 * @author dengtao
 * @date 2020/4/30 10:18
 */
@Data
@ConfigurationProperties(prefix = "taotao.cloud.message.sms.aliyun")
public class AliYunSmsProperties extends BaseSmsProperties {
    private boolean enabled = true;

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 短信类型
     */
    private String type = "aliyun";

    private List<TemplateConfig> configs;

    @Data
    public static class TemplateConfig {
        /**
         * 模板类型
         */
        private String type;
        /**
         * code
         */
        private String code;
    }

}
