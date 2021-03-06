package com.taotao.cloud.taotaoclouddemogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class TaotaoCloudDemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoCloudDemoGatewayApplication.class, args);
    }

}
