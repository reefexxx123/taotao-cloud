package com.taotao.cloud.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * CacheManagerProperties
 *
 * @author dengtao
 * @date 2020/4/30 10:16
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "taotao.cloud.redis.cache-manager")
public class CacheManagerProperties {

    private List<CacheConfig> configs;

    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key
         */
        private String key;
        /**
         * 过期时间，sec
         */
        private long second = 60;
    }
}
