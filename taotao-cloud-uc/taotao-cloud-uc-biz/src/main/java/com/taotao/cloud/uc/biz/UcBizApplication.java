package com.taotao.cloud.uc.biz;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.data.annotation.EnableTaoTaoTenantAutoConfigure;
import com.taotao.cloud.elasticsearch.annotation.EnableTaoTaoSearchClient;
import com.taotao.cloud.elk.annotation.EnableTaoTaoELKAutoConfigure;
import com.taotao.cloud.ribbon.annotation.EnableFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignInterceptor
@EnableTaoTaoSearchClient
@EnableTaoTaoOauth2Client
@EnableTaoTaoTenantAutoConfigure
@EnableTaoTaoELKAutoConfigure
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.uc.biz.mapper")
public class UcBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcBizApplication.class, args);
    }

}
