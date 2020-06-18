package com.taotao.cloud.uc.api.query;

import com.taotao.cloud.common.model.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询DTO
 *
 * @author dengtao
 * @date 2020/5/14 10:44
 */
@Data
@ApiModel(value = "用户查询DTO")
@EqualsAndHashCode(callSuper = true)
public class UserListQuery extends PageParam {
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户真实姓名")
    private String username;
    @ApiModelProperty(value = "部门id")
    private Integer deptId;
    @ApiModelProperty(value = "岗位id")
    private Integer jobId;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "email")
    private String email;
}
