package com.taotao.cloud.auth.config;

import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * SecurityPropertiesConfig
 *
 * @author dengtao
 * @date 2020/4/30 09:06
 */
@Slf4j
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityPropertiesConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(SecurityPropertiesConfig.class, ProjectNameConstant.TAOTAO_CLOUD_AUTH_STARTER, "Security配置已启动");
    }
}
