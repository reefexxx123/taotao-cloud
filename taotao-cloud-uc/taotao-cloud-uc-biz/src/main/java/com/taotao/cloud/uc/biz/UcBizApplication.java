package com.taotao.cloud.uc.biz;

import com.alibaba.csp.sentinel.transport.config.TransportConfig;
import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.data.annotation.EnableTaoTaoTenantClient;
import com.taotao.cloud.elasticsearch.annotation.EnableTaoTaoSearchClient;
import com.taotao.cloud.elk.annotation.EnableTaoTaoELKClient;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTaoTaoFeignInterceptor
@EnableTaoTaoSearchClient
@EnableTaoTaoOauth2Client
@EnableTaoTaoTenantClient
@EnableTaoTaoELKClient
@EnableDiscoveryClient
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = "com.taotao.cloud.uc.biz.mapper")
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@SpringBootApplication
public class UcBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcBizApplication.class, args);
    }

}
