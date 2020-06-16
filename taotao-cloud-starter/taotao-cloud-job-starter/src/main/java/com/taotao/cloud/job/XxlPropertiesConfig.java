package com.taotao.cloud.job;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

/**
 * XxlPropertiesConfig
 *
 * @author dengtao
 * @date 2020/6/16 11:36
 */
@Order(-1000)
@EnableConfigurationProperties(XxlProperties.class)
public class XxlPropertiesConfig {

}
