package com.taotao.cloud.uc.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典dto
 *
 * @author dengtao
 * @date 2020/4/30 11:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDTO {

    private Integer id;

    private String dictName;

    private String dictCode;

    private String description;

    private Integer sort;

    private String remark;

    private String value;
}
