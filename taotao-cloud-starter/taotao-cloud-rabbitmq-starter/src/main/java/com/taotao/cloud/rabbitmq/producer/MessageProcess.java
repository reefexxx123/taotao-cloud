package com.taotao.cloud.rabbitmq.producer;


import com.taotao.cloud.rabbitmq.common.DetailResponse;

public interface MessageProcess<T> {
    DetailResponse process(T message);
}
