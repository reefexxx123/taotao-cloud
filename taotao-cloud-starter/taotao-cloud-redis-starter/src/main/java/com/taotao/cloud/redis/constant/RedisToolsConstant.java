package com.taotao.cloud.redis.constant;

/**
 * redis 工具常量
 *
 * @author dengtao
 * @date 2020/4/30 10:17
 */
public class RedisToolsConstant {
    private RedisToolsConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * single Redis
     */
    public final static int SINGLE = 1;

    /**
     * Redis cluster
     */
    public final static int CLUSTER = 2;
}
