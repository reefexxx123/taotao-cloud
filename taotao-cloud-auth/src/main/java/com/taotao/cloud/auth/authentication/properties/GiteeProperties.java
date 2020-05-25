package com.taotao.cloud.auth.authentication.properties;

import lombok.Data;

/**
 * Gitee Properties
 *
 * @author dengtao
 * @date 2020/4/29 20:19
 */
@Data
public class GiteeProperties extends BaseSocialProperties {

    private String providerId = "gitee";
}
