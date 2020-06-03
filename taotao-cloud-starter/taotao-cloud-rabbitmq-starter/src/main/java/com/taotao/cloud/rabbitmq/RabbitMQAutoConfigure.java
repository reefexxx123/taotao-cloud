package com.taotao.cloud.rabbitmq;

import com.taotao.cloud.rabbitmq.producer.FastBuildRabbitMqProducer;
import com.taotao.cloud.rabbitmq.properties.RabbitMQProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQAutoConfigure
 *
 * @author dengtao
 * @date 2020/5/28 17:17
 */
@Configuration
@ConditionalOnClass(FastBuildRabbitMqProducer.class)
@EnableConfigurationProperties(RabbitMQProperties.class)
@ConditionalOnProperty(prefix = "taotao.cloud.rabbitmq", name = "enabled", havingValue = "true")
public class RabbitMQAutoConfigure {

    @Autowired
    private RabbitMQProperties rabbitMqProperties;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMqProperties.getAddresses());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "taotao.cloud.rabbitmq", value = "enabled", havingValue = "true")
    public FastBuildRabbitMqProducer fastRabbitMqProducer(ConnectionFactory connectionFactory) {
        return new FastBuildRabbitMqProducer(connectionFactory);
    }
}
