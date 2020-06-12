package com.taotao.cloud.uc.api.query;

import com.taotao.cloud.common.model.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册DTO
 *
 * @author dengtao
 * @date 2020/5/14 10:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQuery extends PageParam {
    private Integer userId;
    private String username;
    private Integer deptId;
    private Integer jobId;
    private String phone;
    private String email;
}
