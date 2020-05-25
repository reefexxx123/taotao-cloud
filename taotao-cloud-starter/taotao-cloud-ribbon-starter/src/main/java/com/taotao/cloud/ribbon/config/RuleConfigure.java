package com.taotao.cloud.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.taotao.cloud.ribbon.rule.CustomIsolationRule;
import org.springframework.context.annotation.Bean;

/**
 * @author dengtao
 * @date 2019/9/3
 */
public class RuleConfigure {

    @Bean
    public IRule isolationRule() {
        return new CustomIsolationRule();
    }
}
