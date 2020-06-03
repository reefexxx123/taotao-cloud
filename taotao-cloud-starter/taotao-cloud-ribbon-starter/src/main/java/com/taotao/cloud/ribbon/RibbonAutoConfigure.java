package com.taotao.cloud.ribbon;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.ribbon.properties.RibbonProperties;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.DefaultPropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon扩展配置类
 *
 * @author dengtao
 * @date 2018/11/17 9:24
 */
@EnableConfigurationProperties(RibbonProperties.class)
public class RibbonAutoConfigure implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(RibbonAutoConfigure.class, StarterNameConstant.TAOTAO_CLOUD_RIBBON_STARTER, "ribbon模块已启动");
    }

    @Autowired
    private HttpClient httpClient;

    @Bean
    public DefaultPropertiesFactory defaultPropertiesFactory() {
        return new DefaultPropertiesFactory();
    }

    /**
     * httpclient 实现的ClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }

}
