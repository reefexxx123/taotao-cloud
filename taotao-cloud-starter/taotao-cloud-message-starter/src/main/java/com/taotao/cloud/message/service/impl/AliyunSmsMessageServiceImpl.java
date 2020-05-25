/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.message.service
 * Date: 2020/5/3 11:06
 * Author: dengtao
 */
package com.taotao.cloud.message.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.taotao.cloud.common.exception.MessageException;
import com.taotao.cloud.message.domain.SmsResponse;
import com.taotao.cloud.message.enums.SmsEnum;
import com.taotao.cloud.message.properties.AliYunSmsProperties;
import com.taotao.cloud.message.properties.SmsProperties;
import com.taotao.cloud.message.service.IAliyunSmsMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/3 11:06
 */
@AllArgsConstructor
@Slf4j
public class AliyunSmsMessageServiceImpl implements IAliyunSmsMessageService {

    private final SmsProperties smsProperties;

    @Override
    public SmsResponse sendSms(String[] phoneNumbers, String sign, SmsEnum smsEnum) {
        AliYunSmsProperties aliyunProperties = smsProperties.getAliyun();
        String code = RandomStringUtils.random(aliyunProperties.getLength(), false, true);
        // 指定模板ID单发短信
        try {
            String[] params = {code, "5"};
            List<AliYunSmsProperties.TemplateConfig> templateConfigs = aliyunProperties.getConfigs();
            List<AliYunSmsProperties.TemplateConfig> smsConfig = templateConfigs.stream().filter((templateConfig -> smsEnum.getType().equals(templateConfig.getType()))).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(smsConfig)) {
                throw new MessageException("未获取到模板code");
            }
            AliYunSmsProperties.TemplateConfig templateConfig = smsConfig.get(0);

            SmsMultiSender msender = new SmsMultiSender(Integer.parseInt(aliyunProperties.getAccessKeyId()), aliyunProperties.getAccessKeySecret());
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                    Integer.parseInt(templateConfig.getCode()), params, sign, "", "");
            SmsResponse smsResponse = new SmsResponse();
            JSONObject jsonObject = result.parseToJson(result.getResponse());
            int resultCode = jsonObject.getInt("result");
            String errmsg = jsonObject.getString("errmsg");
            if (0 == resultCode && StrUtil.isNotEmpty(errmsg) && "OK".equals(errmsg)) {
                log.info(phoneNumbers[0] + ",发送短信成功");
                smsResponse.setSmsCode(code);
                smsResponse.setSmsPhone(phoneNumbers[0]);
                smsResponse.setSmsTime(System.nanoTime() + "");
                return smsResponse;
            } else {
                log.error(phoneNumbers[0] + ",发送短信失败:{}", jsonObject.get("Message"));
            }
            return smsResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
