package com.taotao.cloud.rabbitmq.comsumer;


import com.taotao.cloud.rabbitmq.common.DetailResponse;

/**
 * Created by littlersmall on 16/5/12.
 */
public interface MessageConsumer {
    DetailResponse consume();
}
