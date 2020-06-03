package com.taotao.cloud.ribbon;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.ribbon.filter.LbIsolationFilter;
import com.taotao.cloud.ribbon.filter.TenantFilter;
import com.taotao.cloud.ribbon.filter.TraceFilter;
import com.taotao.cloud.ribbon.properties.RibbonIsolationProperties;
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
@EnableConfigurationProperties(RibbonIsolationProperties.class)
public class RibbonAutoConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(RibbonAutoConfiguration.class, StarterNameConstant.TAOTAO_CLOUD_RIBBON_STARTER, "ribbon模块已启动");
    }

    @Bean
    public DefaultPropertiesFactory defaultPropertiesFactory() {
        return new DefaultPropertiesFactory();
    }

    @Bean
    public LbIsolationFilter lbIsolationFilter() {
        return new LbIsolationFilter();
    }

    @Bean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    @Bean
    public TenantFilter tenantFilter() {
        return new TenantFilter();
    }

    /**
     * httpclient 实现的ClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory(@Autowired HttpClient httpClient) {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }

}
