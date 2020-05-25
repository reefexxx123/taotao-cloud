package com.taotao.cloud.elk.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ElkProperties
 *
 * @author dengtao
 * @date 2020/5/3 16:18
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "toaotao.cloud.elk")
public class ElkProperties {
    private boolean enabled = true;

    @Value("${spring.application.name:}")
    private String appName;
    @Value("${spring.application.name:}")
    private String springAppName;

    private String[] destinations = {"127.0.0.1:4560"};
}
