package com.taotao.cloud.log.service.impl;

import com.taotao.cloud.common.utils.GsonUtil;
import com.taotao.cloud.log.feign.RemoteLogService;
import com.taotao.cloud.log.service.ISysLogService;
import com.taotao.cloud.uc.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 审计日志实现类-打印日志
 *
 * @author dengtao
 * @date 2020/5/2 11:18
 */
@Slf4j
public class KafkaSysLogServiceImpl implements ISysLogService {

    @Value("${spring.application.name:----}")
    private String appName;

    public static final String SYS_LOG_TOPIC = "taotao-cloud-business-log-topic-";

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void save(SysLog sysLog) {
        String obj = GsonUtil.toGson(sysLog);
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        String topic = SYS_LOG_TOPIC.concat(appName).concat("-").concat(format);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("kafka主题: {}, 远程日志记录失败：{}", topic, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info("kafka主题: {}, 远程日志记录成功：{}", topic, sysLog);
            }
        });
    }
}

