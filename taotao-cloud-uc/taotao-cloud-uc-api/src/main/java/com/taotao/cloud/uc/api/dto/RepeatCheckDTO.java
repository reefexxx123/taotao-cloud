package com.taotao.cloud.uc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 重复校验DTO
 *
 * @author dengtao
 * @date 2020/5/2 16:40
*/
@Data
@AllArgsConstructor
public class RepeatCheckDTO {

    /**
     * 字段值 邮箱 手机号 用户名
     */
    private String fieldVal;
    /**
     * 指用户id 主要作用编辑情况过滤自己的校验
     */
    private Integer dataId;

}
