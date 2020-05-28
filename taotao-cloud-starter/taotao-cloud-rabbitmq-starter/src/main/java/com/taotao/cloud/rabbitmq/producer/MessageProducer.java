package com.taotao.cloud.rabbitmq.producer;


import com.taotao.cloud.rabbitmq.common.DetailResponse;

public interface MessageProducer {


    DetailResponse send(Object message);

}
