package com.taotao.cloud.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 默认异步任务配置
 *
 * @author dengtao
 * @date 2020/5/2 09:12
 */
@Data
@EnableAsync(proxyTargetClass = true)
public class DefaultAsyncTaskConfig {
    /**
     * 线程池维护线程的最小数量.
     */
    @Value("${taotao.cloud.asyc-task.corePoolSize:10}")
    private int corePoolSize;
    /**
     * 线程池维护线程的最大数量
     */
    @Value("${taotao.cloud.asyc-task.maxPoolSize:200}")
    private int maxPoolSize;
    /**
     * 队列最大长度
     */
    @Value("${taotao.cloud.asyc-task.queueCapacity:10}")
    private int queueCapacity;
    /**
     * 线程池前缀
     */
    @Value("${taotao.cloud.asyc-task.threadNamePrefix:TaotaoExecutor-}")
    private String threadNamePrefix;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new CustomThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        /**
         rejection-policy：当pool已经达到max size的时候，如何处理新任务
         CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
