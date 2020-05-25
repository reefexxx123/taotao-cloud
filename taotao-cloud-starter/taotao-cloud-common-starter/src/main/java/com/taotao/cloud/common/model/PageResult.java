package com.taotao.cloud.common.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author dengtao
 * @date 2020/4/29 15:40
 */
@Data
@Builder
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -275582248840137389L;

    private int code;
    private String message;
    private long total;
    private long pageSize;
    private long currentPage;
    private List<T> data;

}
