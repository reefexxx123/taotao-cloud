package com.taotao.cloud.uc.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * dengtao
 *
 * @author dengtao
 * @date 2020/6/15 11:00
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptDTO {

    private static final long serialVersionUID = 1L;

    private Integer deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;


}
