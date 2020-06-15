package com.taotao.cloud.uc.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * dengtao
 *
 * @author dengtao
 * @date 2020/6/15 11:00
 */
@Setter
@Getter
public class RoleDTO {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String delFlag;
    private int dsType;
    List<Integer> permissionIds;
    List<Integer> roleDepts;



}
