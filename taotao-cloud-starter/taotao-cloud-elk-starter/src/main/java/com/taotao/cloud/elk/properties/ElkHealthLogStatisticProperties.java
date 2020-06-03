/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.elk.properties
 * Date: 2020/5/3 15:34
 * Author: dengtao
 */
package com.taotao.cloud.elk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "taotao.cloud.elk.log.statistic")
public class ElkHealthLogStatisticProperties {

    private boolean enabled = false;
}
