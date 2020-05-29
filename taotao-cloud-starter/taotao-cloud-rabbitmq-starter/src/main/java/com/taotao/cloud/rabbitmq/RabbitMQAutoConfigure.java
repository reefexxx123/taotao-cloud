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
public class RabbitMQAutoConfigure {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMQProperties.getAddresses());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "taotao.cloud.rabbitmq", value = "enabled", havingValue = "true")
    public FastBuildRabbitMqProducer fastRabbitMQProducer(ConnectionFactory connectionFactory) {
        return new FastBuildRabbitMqProducer(connectionFactory);
    }
}
