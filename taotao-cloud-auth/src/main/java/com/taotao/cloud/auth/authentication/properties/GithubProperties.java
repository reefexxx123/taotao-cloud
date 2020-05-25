package com.taotao.cloud.auth.authentication.properties;

import lombok.Data;

/**
 * github Properties
 *
 * @author dengtao
 * @date 2020/4/29 20:19
 */
@Data
public class GithubProperties extends BaseSocialProperties {

    private String providerId = "github";
}
