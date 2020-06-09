/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.common.model
 * Date: 2020/6/9 10:06
 * Author: dengtao
 */
package com.taotao.cloud.common.model;

import lombok.Data;

/**
 * 基础分页查询参数<br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/6/9 10:06
 */
@Data
public class PageParam {
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;
}
