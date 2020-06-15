//package com.taotao.cloud.shardingjdbc;
//
//import com.taotao.cloud.shardingjdbc.properties.ShardingJdbcProperties;
//import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
//import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
//import org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties;
//import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
//import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
//import org.springframework.beans.BeansException;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.beans.ConstructorProperties;
//
//
//@Configuration
//@EnableConfigurationProperties({ShardingJdbcProperties.class, SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootEncryptRuleConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class})
//@ConditionalOnProperty(name = "taotao.cloud.shardingjdbc.enabled", havingValue = "false")
//public class ShardingJdbcConfiguration extends SpringBootConfiguration implements ApplicationContextAware {
//
//    @Override
//    public void setApplicationContext(ApplicationContext var1) throws BeansException {
//        super.setEnvironment(var1.getEnvironment());
//    }
//
//    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "encryptProperties", "propMapProperties"})
//    public ShardingJdbcConfiguration(SpringBootShardingRuleConfigurationProperties shardingProperties,
//                                     SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties,
//                                     SpringBootEncryptRuleConfigurationProperties encryptProperties,
//                                     SpringBootPropertiesConfigurationProperties propMapProperties) {
//        super(shardingProperties, masterSlaveProperties, encryptProperties, propMapProperties);
//    }
//
//    @Bean
//    @ConditionalOnProperty(name = "taotao.cloud.shardingjdbc.aspect.enabled", havingValue = "false")
//    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
//    public ShardingJdbcDynamicDataSourceAspect shardingJdbcDynamicDataSourceAspect() {
//        return new ShardingJdbcDynamicDataSourceAspect();
//    }
//}
