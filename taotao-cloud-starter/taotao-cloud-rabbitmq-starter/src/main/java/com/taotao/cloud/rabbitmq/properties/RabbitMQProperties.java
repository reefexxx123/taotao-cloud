package com.taotao.cloud.rabbitmq.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RabbitMQProperties
 *
 * @author dengtao
 * @date 2020/5/28 17:35
 */
@Data
@ConfigurationProperties(prefix = "taotao.cloud.rabbitmq")
public class RabbitMQProperties {

    private boolean enabled;

    private String addresses;

    private String username;

    private String password;

    private String virtualHost;

    private boolean publisherConfirms = true;
}
