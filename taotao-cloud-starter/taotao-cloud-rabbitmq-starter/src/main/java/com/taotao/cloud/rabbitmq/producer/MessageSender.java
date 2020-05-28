package com.taotao.cloud.rabbitmq.producer;


import com.taotao.cloud.rabbitmq.common.DetailResponse;

public interface MessageSender {


    DetailResponse send(Object message);

    DetailResponse send(MessageWithTime messageWithTime);
}
