package com.taotao.cloud.common.model;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体绑定spring security
 *
 * @author dengtao
 * @date 2020/4/30 10:27
 */
@Data
public class SecurityMenu implements Serializable {
    private static final long serialVersionUID = 749360940290141180L;

    private String url;
    private String path;
    private Integer sort;
    private Integer type;
    private Boolean hidden;
    private String pathMethod;

}
