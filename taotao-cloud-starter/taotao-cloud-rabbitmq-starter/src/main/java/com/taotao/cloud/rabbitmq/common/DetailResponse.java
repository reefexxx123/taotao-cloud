package com.taotao.cloud.rabbitmq.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DetailResponse
 *
 * @author dengtao
 * @date 2020/5/28 17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailResponse {

    private boolean ifSuccess;

    private String errorCode;

    private String errMsg;
}
