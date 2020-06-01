package com.taotao.cloud.uc.biz;

import com.taotao.cloud.elasticsearch.annotation.EnableSearchClient;
import com.taotao.cloud.ribbon.annotation.EnableFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignInterceptor
@EnableSearchClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.uc.biz.mapper")
@ComponentScan(value = "com.taotao.cloud")
public class UcBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcBizApplication.class, args);
    }

}
