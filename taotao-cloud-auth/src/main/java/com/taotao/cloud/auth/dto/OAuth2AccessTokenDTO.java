/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.auth.dto
 * Date: 2020/5/14 17:03
 * Author: dengtao
 */
package com.taotao.cloud.auth.dto;

import com.taotao.cloud.common.model.SecurityUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/14 17:03
 */
@Data
public class OAuth2AccessTokenDTO {

    /**
     * token 信息
     */
    @ApiModelProperty(value = "token 信息")
    private OAuth2AccessToken token;

    /**
     * 登录用户信息
     */
    @ApiModelProperty(value = "登录用户")
    private SecurityUser user;
}
