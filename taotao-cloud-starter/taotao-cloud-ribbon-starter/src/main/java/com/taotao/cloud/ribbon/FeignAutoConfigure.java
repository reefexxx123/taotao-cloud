package com.taotao.cloud.ribbon;

import com.google.gson.Gson;
import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.utils.LogUtils;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * Feign统一配置
 *
 * @author dengtao
 * @date 2018/9/18 14:04
 */
public class FeignAutoConfigure implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(RibbonAutoConfigure.class, ProjectNameConstant.TAOTAO_CLOUD_FEIGN_STARTER, "feign模块已启动");
    }

    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    Retryer feignRetryer() {
        return  new Retryer.Default();
    }

    @Bean
    FeignClientErrorDecoder feignClientErrorDecoder() {
        return new FeignClientErrorDecoder();
    }

    @Slf4j
    public static class FeignClientErrorDecoder implements feign.codec.ErrorDecoder {
        private final Gson gson = new Gson();

        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() != HttpStatus.OK.value()) {
                if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                    String errorContent;
                    try {
                        errorContent = Util.toString(response.body().asReader());
                        return gson.fromJson(errorContent, BaseException.class);
                    } catch (IOException e) {
                        log.error("handle error exception");
                        return new BaseException("500", e);
                    }
                }
            }
            return new BaseException("500");
        }
    }
}
