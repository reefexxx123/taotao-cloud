package com.taotao.cloud.log.properties;

import com.taotao.cloud.common.enums.LogTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 审计日志配置
 *
 * @author dengtao
 * @date 2020/5/2 11:19
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.log")
public class SysLogProperties {
    /**
     * 是否开启审计日志
     */
    private Boolean enabled = false;

    /**
     * 日志记录类型(logger/redis/db/es)
     */
    private String type = LogTypeEnum.DB.getName();

}
