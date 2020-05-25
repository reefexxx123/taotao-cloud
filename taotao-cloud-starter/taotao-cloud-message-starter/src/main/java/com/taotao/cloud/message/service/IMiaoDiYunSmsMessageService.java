/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.message.service
 * Date: 2020/5/3 11:05
 * Author: dengtao
 */
package com.taotao.cloud.message.service;

import com.taotao.cloud.message.domain.SmsResponse;
import com.taotao.cloud.message.enums.SmsEnum;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/3 11:05
 */
public interface IMiaoDiYunSmsMessageService {
    /**
     * 秒滴云短信发送
     *
     * @param phone
     * @param smsContent
     * @param code
     * @return com.taotao.cloud.message.domain.SmsResponse
     * @author dengtao
     * @date 2020/5/3 11:05
     */
    SmsResponse sendSms(String phone, String smsContent, String code);
}
