package com.taotao.cloud.auth.authentication.properties;

import lombok.Data;

/**
 * weixin Properties
 *
 * @author dengtao
 * @date 2020/4/29 20:21
 */
@Data
public class WeiXinProperties extends BaseSocialProperties {

    private String providerId = "weixin";
}
