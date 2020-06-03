package com.taotao.cloud.ribbon;

import com.google.gson.Gson;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.utils.LogUtil;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Feign统一配置
 *
 * @author dengtao
 * @date 2018/9/18 14:04
 */
public class FeignAutoConfigure implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(RibbonAutoConfigure.class, StarterNameConstant.TAOTAO_CLOUD_FEIGN_STARTER, "feign模块已启动");
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    Retryer feignRetryer() {
        return new Retryer.Default();
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
            String errorContent;
            try {
                errorContent = Util.toString(response.body().asReader(Charset.defaultCharset()));
                return gson.fromJson(errorContent, BaseException.class);
            } catch (IOException e) {
                e.printStackTrace();
                return new BaseException("500", e);
            }
        }
    }
}
