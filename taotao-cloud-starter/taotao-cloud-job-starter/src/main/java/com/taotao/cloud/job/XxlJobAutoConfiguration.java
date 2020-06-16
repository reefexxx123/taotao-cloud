package com.taotao.cloud.job;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.utils.AddrUtil;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * XxlJobConfiguration
 *
 * @author dengtao
 * @date 2020/6/16 11:36
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(XxlProperties.class)
@ConditionalOnProperty(name = "taotao.cloud.xxl.job.enabled", havingValue = "true")
public class XxlJobAutoConfiguration {

    @Resource
    XxlProperties xxlProperties;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        String appName = xxlProperties.getAppName().length() == 0 ? xxlProperties.getSpringAppName()
                : xxlProperties.getAppName();
        if (appName.length() == 0) {
            throw new BaseException("缺少参数：taotao.cloud.xxl.job.executor.appName");
        }
        String adminAddresses = xxlProperties.getAdminAddresses();
        if(StrUtil.isBlank(adminAddresses)){
            throw new BaseException("缺少参数：taotao.cloud.xxl.job.executor.adminAddresses");
        }

        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(adminAddresses);
        executor.setAppname(appName);
        executor.setIp(xxlProperties.getIp());

        if (StrUtil.isEmpty(xxlProperties.getIp())) {
            executor.setIp(AddrUtil.getLocalAddr());
        }
        executor.setPort(xxlProperties.getPort());
        executor.setAccessToken(xxlProperties.getAccessToken());
        executor.setLogPath(xxlProperties.getLogPath());
        executor.setLogRetentionDays(xxlProperties.getLogRetentionDays());
        log.info("[TAOTAO CLOUD][" + StarterNameConstant.TAOTAO_CLOUD_JOB_STARTER + "]" + "job模块已启动");
        return executor;
    }

}
