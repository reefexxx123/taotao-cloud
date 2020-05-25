package com.taotao.cloud.message.domain;

import lombok.Data;

/**
 * SmsResponse
 *
 * @author dengtao
 * @date 2020/4/30 10:17
 */
@Data
public class SmsResponse {
    private String smsPhone;
    private String smsTime;
    private String smsCode;
}
