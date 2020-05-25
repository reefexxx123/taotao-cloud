//package com.taotao.cloud.gateway.config;
//
//import com.alibaba.cloud.nacos.NacosConfigProperties;
//import com.taotao.cloud.gateway.route.NacosRouteDefinitionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 动态路由配置
// *
// * @author dengtao
// * @date 2020/5/2 19:33
// */
//@Configuration
//@ConditionalOnProperty(prefix = "taotao.cloud.gateway.dynamicRoute", name = "enabled", havingValue = "true")
//public class DynamicRouteConfig {
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    /**
//     * Nacos实现方式
//     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "taotao.cloud.gateway.dynamicRoute", name = "dataType", havingValue = "nacos", matchIfMissing = true)
//    public class NacosDynRoute {
//        @Autowired
//        private NacosConfigProperties nacosConfigProperties;
//
//        @Bean
//        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository() {
//            return new NacosRouteDefinitionRepository(publisher, nacosConfigProperties);
//        }
//    }
//}
