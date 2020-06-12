package com.taotao.cloud.log.service.impl;

import com.taotao.cloud.common.utils.GsonUtil;
import com.taotao.cloud.log.service.ISysLogService;
import com.taotao.cloud.redis.repository.RedisRepository;
import com.taotao.cloud.uc.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 审计日志实现类-打印日志
 *
 * @author dengtao
 * @date 2020/5/2 11:18
 */
@Slf4j
@ConditionalOnProperty(name = "taotao.cloud.log.type", havingValue = "redis")
public class RedisSysLogServiceImpl implements ISysLogService {

    private final static String SYS_LOG = "sys:log:request:";

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public void save(SysLog sysLog) {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault()).format(Instant.now());
        Long index = redisRepository.leftPush(SYS_LOG + date, GsonUtil.toGson(sysLog));
        if (index > 0) {
            log.info("redis远程日志记录成功：{}", sysLog);
        } else {
            log.error("redis远程日志记录失败：{}", sysLog);
        }
    }
}
