package com.taotao.cloud.shardingjdbc;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.ConstructorProperties;

/**
 * ShardingJdbcConfiguration
 *
 * @author dengtao
 * @date 2020/6/22 17:30
 */
@Configuration
@EnableConfigurationProperties({ShardingJdbcProperties.class, SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootEncryptRuleConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class})
@ConditionalOnProperty(name = "taotao.cloud.shardingsphere.enabled", havingValue = "true")
public class ShardingJdbcConfiguration extends SpringBootConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        super.setEnvironment(context.getEnvironment());
    }

    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "encryptProperties", "propMapProperties"})
    public ShardingJdbcConfiguration(SpringBootShardingRuleConfigurationProperties shardingProperties, SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties, SpringBootEncryptRuleConfigurationProperties encryptProperties, SpringBootPropertiesConfigurationProperties propMapProperties) {
        super(shardingProperties, masterSlaveProperties, encryptProperties, propMapProperties);
    }

    @Bean
    public DataSourceShardingAlgorithm dataSourceShardingAlgorithm() {
        return new DataSourceShardingAlgorithm();
    }

}
