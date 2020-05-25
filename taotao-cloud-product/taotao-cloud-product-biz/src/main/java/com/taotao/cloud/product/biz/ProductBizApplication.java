package com.taotao.cloud.product.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.taotao.coud.product.biz.mapper")
public class ProductBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBizApplication.class, args);
    }

}
