package com.taotao.cloud.rabbitmq.comsumer;


import com.taotao.cloud.rabbitmq.common.DetailResponse;

/**
 * MessageConsumer
 *
 * @author dengtao
 * @date 2020/5/28 17:27
 */
public interface MessageConsumer {

    DetailResponse consume();
}
