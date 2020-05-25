package com.taotao.cloud.message.properties;

import lombok.Data;

/**
 * BaseSmsProperties
 *
 * @author dengtao
 * @date 2020/4/30 10:18
*/
@Data
public abstract class BaseSmsProperties {

    private String accessKeyId;
    private String accessKeySecret;

}
