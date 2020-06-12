package com.taotao.cloud.auth;

import com.taotao.cloud.auth.annotation.EnableTaoTaoOauth2Client;
import com.taotao.cloud.ribbon.annotation.EnableTaoTaoFeignInterceptor;
import com.taotao.cloud.social.annotation.EnableSocialAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dengtao
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableTaoTaoFeignInterceptor
@EnableSocialAutoConfiguration
@EnableTaoTaoOauth2Client
@EnableFeignClients(basePackages = "com.taotao.cloud.*.api.feign")
@MapperScan(value = "com.taotao.cloud.auth.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    /**
     * 装配BCryptPasswordEncoder用户密码的匹配
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
