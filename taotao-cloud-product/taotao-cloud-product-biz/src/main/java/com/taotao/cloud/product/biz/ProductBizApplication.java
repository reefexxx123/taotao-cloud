package com.taotao.cloud.product.biz;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.data.annotation.EnableTaoTaoTenantClient;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTaoTaoFeignInterceptor
@EnableTaoTaoOauth2Client
@EnableTaoTaoTenantClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.product.biz.mapper")
public class ProductBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBizApplication.class, args);
    }

}
