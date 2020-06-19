package com.taotao.cloud.order.biz;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.data.annotation.EnableTaoTaoTenantClient;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableDiscoveryClient
@EnableTaoTaoFeignInterceptor
@EnableTaoTaoOauth2Client
@EnableTaoTaoTenantClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.order.biz.mapper")
public class OrderBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBizApplication.class, args);
    }
}

