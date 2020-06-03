package com.taotao.cloud.file.biz;

import com.taotao.cloud.file.biz.properties.FileServerProperties;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableConfigurationProperties(FileServerProperties.class)
@EnableFeignClients
@EnableTaoTaoFeignInterceptor
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "com.taotao.cloud.file.biz.mapper")
public class FileBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileBizApplication.class, args);
    }

}
