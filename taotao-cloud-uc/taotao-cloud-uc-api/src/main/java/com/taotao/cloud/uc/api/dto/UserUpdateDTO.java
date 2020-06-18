package com.taotao.cloud.uc.api.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户注册DTO
 *
 * @author dengtao
 * @date 2020/5/14 10:44
 */
@Data
public class UserUpdateDTO {
    private Integer userId;
    private String nickname;
    private String password;
    private String username;
    private Integer deptId;
    private Integer jobId;
    private String phone;
    private String email;
    private String avatar;
    private String lockFlag;
    private String delFlag;
    private List<Integer> roleList;
    private List<Integer> deptList;
}
