package com.taotao.cloud.file.biz.properties;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dengtao
 * @date 2020/6/15 11:13
 */
@Setter
@Getter
public class OssProperties {
    /**
     * 密钥key
     */
    private String accessKey;
    /**
     * 密钥密码
     */
    private String accessKeySecret;
    /**
     * 端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 说明
     */
    private String domain;
}
