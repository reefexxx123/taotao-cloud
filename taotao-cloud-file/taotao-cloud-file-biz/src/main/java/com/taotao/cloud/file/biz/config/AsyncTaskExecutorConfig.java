package com.taotao.cloud.file.biz.config;

import com.taotao.cloud.common.config.DefaultAsyncTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author zlt
 * 线程池配置、启用异步
 * @Async quartz 需要使用
 */
@Configuration
public class AsyncTaskExecutorConfig extends DefaultAsyncTaskConfig {

}
