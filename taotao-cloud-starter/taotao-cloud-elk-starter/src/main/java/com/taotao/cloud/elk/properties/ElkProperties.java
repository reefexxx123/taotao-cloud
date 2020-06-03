package com.taotao.cloud.elk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * ElkProperties
 *
 * @author dengtao
 * @date 2020/5/3 16:18
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "toaotao.cloud.elk")
public class ElkProperties {

    private boolean enabled = false;

    private String appName = "";

    private String springAppName = "";

    private String[] destinations = {"127.0.0.1:4560"};
}
