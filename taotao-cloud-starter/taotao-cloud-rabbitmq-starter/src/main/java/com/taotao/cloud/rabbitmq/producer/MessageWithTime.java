package com.taotao.cloud.rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * MessageWithTime
 *
 * @author dengtao
 * @date 2020/5/28 17:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageWithTime {
    private long id;
    private long time;
    private Object message;
}
