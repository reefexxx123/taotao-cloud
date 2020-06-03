package com.taotao.cloud.auth.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置
 *
 * @author dengtao
 * @date 2020/5/2 11:20
 */
@Data
public class SmsCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
