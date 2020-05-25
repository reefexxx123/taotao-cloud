/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.auth.dto
 * Date: 2020/4/30 17:10
 * Author: dengtao
 */
package com.taotao.cloud.auth.dto;

import lombok.Data;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/4/30 17:10
 */
@Data
public class UserTokenDTO {
    private String username;
    private String password;
}
