package com.taotao.cloud.auth;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import com.taotao.cloud.social.annotation.EnableTaoTaoSocialClient;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dengtao
 */
@EnableTaoTaoFeignInterceptor
@EnableTaoTaoSocialClient
@EnableTaoTaoOauth2Client
@EnableDiscoveryClient
@EnableTransactionManagement(proxyTargetClass = true)
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.auth.mapper")
@SpringBootApplication()
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
