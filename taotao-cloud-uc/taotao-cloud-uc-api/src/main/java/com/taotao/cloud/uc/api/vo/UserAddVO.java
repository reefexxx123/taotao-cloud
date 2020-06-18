package com.taotao.cloud.uc.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户注册VO
 *
 * @author dengtao
 * @date 2020/5/14 10:44
 */
@Data
@Builder
@ApiModel(value = "用户注册VO")
public class UserAddVO {

    @ApiModelProperty(value = "真实用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
