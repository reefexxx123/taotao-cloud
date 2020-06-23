package com.taotao.cloud.product.biz;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.data.annotation.EnableTaoTaoTenantClient;
import com.taotao.cloud.elasticsearch.annotation.EnableTaoTaoSearchClient;
import com.taotao.cloud.elk.annotation.EnableTaoTaoELKClient;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTaoTaoFeignInterceptor
@EnableTaoTaoSearchClient
@EnableTaoTaoOauth2Client
@EnableTaoTaoTenantClient
@EnableTaoTaoELKClient
@EnableDiscoveryClient
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = "com.taotao.cloud.product.biz.mapper")
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@SpringBootApplication
public class ProductBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBizApplication.class, args);
    }

}
