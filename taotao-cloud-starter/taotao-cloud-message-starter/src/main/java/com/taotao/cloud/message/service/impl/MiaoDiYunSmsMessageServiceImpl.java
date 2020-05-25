/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.message.service.impl
 * Date: 2020/5/3 11:45
 * Author: dengtao
 */
package com.taotao.cloud.message.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.message.domain.SmsResponse;
import com.taotao.cloud.message.properties.MiaoDiYunSmsProperties;
import com.taotao.cloud.message.properties.SmsProperties;
import com.taotao.cloud.message.service.IMiaoDiYunSmsMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/3 11:45
 */
@Slf4j
@AllArgsConstructor
public class MiaoDiYunSmsMessageServiceImpl implements IMiaoDiYunSmsMessageService {

    private final SmsProperties smsProperties;

    private final RestTemplate restTemplate;

    @Override
    public SmsResponse sendSms(String phone, String smsContent, String code) {
        MiaoDiYunSmsProperties miaoDiYunSmsProperties = smsProperties.getMiaodiyun();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeStamp = format.format(date);
        String sig = MD5(miaoDiYunSmsProperties.getAccessKeyId(), miaoDiYunSmsProperties.getAccessKeySecret(), timeStamp);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("accountSid", miaoDiYunSmsProperties.getAccessKeyId());
        map.add("smsContent", smsContent);
        map.add("to", phone);
        map.add("timestamp", timeStamp);
        map.add("sig", sig);

        String res = restTemplate.postForObject(miaoDiYunSmsProperties.getBaseUrl(), map, String.class);

        JSONObject jsonObject = JSONObject.parseObject(res);
        SmsResponse smsResponse = new SmsResponse();
        String respCode = jsonObject.getString("respCode");

        if (StrUtil.isNotEmpty(respCode) && "0000".equals(respCode)) {
            log.info(phone + ",发送短信成功");
            smsResponse.setSmsCode(code);
            smsResponse.setSmsPhone(phone);
            smsResponse.setSmsTime(System.nanoTime() + "");
            return smsResponse;
        } else {
            log.error(phone + ",发送短信失败:{}", jsonObject.getString("respDesc"));
        }
        return null;
    }

    /**
     * MD5算法 动态参数
     *
     * @param args
     * @return
     */
    public static String MD5(String... args) {
        StringBuffer result = new StringBuffer();
        if (args == null || args.length == 0) {
            return "";
        } else {
            StringBuffer str = new StringBuffer();
            for (String string : args) {
                str.append(string);
            }
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(str.toString().getBytes());
                for (byte b : bytes) {
                    String hex = Integer.toHexString(b & 0xff);
                    if (hex.length() == 1) {
                        result.append("0" + hex);
                    } else {
                        result.append(hex);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
