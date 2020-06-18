package com.taotao.cloud.uc.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户注册DTO
 *
 * @author dengtao
 * @date 2020/5/14 10:44
 */
@Data
@ApiModel(value = "用户注册DTO")
public class UserAddDTO {

    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    @Max(value = 10, message = "昵称不能超过10个字符")
    private String nickname;

    @ApiModelProperty(value = "真实用户名", required = true)
    @NotBlank(message = "真实用户名不能为空")
    @Max(value = 10, message = "真实用户名不能超过10个字符")
    private String username;

    @ApiModelProperty(value = "用户类型 1前端用户 2商户用户 3后台管理用户", required = true)
    @NotBlank(message = "用户类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$", message = "手机号码不正确")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式错误")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "部门ID", required = true)
    @NotBlank(message = "部门ID不能为空")
    private Integer deptId;

    @ApiModelProperty(value = "岗位ID", required = true)
    @NotBlank(message = "岗位ID不能为空")
    private Integer jobId;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
