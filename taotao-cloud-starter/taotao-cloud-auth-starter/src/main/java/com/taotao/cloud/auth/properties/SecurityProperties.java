package com.taotao.cloud.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * SecurityProperties
 *
 * @author dengtao
 * @date 2020/5/2 11:21
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.oauth2.security")
public class SecurityProperties {
    /**
     * 是否开启权限认证 总开关
     */
    private boolean enabled = true;

    private AuthProperties auth = new AuthProperties();

    private PermitProperties ignore = new PermitProperties();

    private SmsCodeProperties code = new SmsCodeProperties();
}
