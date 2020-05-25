//package com.taotao.cloud.message.dingding;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 钉钉配置类
// *
// * @author dengtao
// * @date 2020/5/3 10:43
//*/
//@Configuration
//@EnableConfigurationProperties(DingdingProperties.class)
//@ConditionalOnProperty(name = "taotao.cloud.message.dingding.enabled", havingValue = "true")
//public class DingdingConfiguration  implements InitializingBean {
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//
//    @Bean
//    public DingdingProvider getDingding() {
//        return new DingdingProvider();
//    }
//}
