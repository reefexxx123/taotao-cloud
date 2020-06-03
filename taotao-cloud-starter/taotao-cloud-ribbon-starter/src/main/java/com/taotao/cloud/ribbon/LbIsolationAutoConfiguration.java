package com.taotao.cloud.ribbon;

import com.taotao.cloud.ribbon.config.RuleConfigure;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * Ribbon扩展配置类
 *
 * @author dengtao
 * @date 2018/11/17 9:24
 */
@ConditionalOnProperty(value = "taotao.cloud.ribbon.isolation.enabled", havingValue = "true")
@RibbonClients(defaultConfiguration = {RuleConfigure.class})
public class LbIsolationAutoConfiguration {

}
