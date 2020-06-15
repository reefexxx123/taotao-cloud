/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.log
 * Date: 2020/6/12 16:28
 * Author: dengtao
 */
package com.taotao.cloud.log;

import com.github.danielwegener.logback.kafka.KafkaAppender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/6/12 16:28
 */
public class CustomKafkaAppender<E> extends KafkaAppender<E> {
    public CustomKafkaAppender() {
        super();
    }

    @Override
    public void doAppend(E e) {
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        if (!topic.endsWith(format)) {
            this.topic = topic.concat("-").concat(format);
        }
        super.doAppend(e);
    }
}
