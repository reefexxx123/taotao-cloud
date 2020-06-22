package com.taotao.cloud.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * 网关
 *
 * @author dengtao
 * @date 2020/6/1 16:05
 */
@EnableDiscoveryClient
@EnableWebFluxSecurity
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GatewayApplication.class, args);
        new SpringApplicationBuilder(GatewayApplication.class).web(WebApplicationType.REACTIVE).run(args);
    }

}
