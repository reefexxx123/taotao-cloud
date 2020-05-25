package com.taotao.cloud.auth;

import com.taotao.cloud.message.enums.SmsEnum;
import com.taotao.cloud.message.service.IAliyunSmsMessageService;
import com.taotao.cloud.message.service.impl.AliyunSmsMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    private IAliyunSmsMessageService aliyunSmsMessageService;
    @Test
    void contextLoads() {
        String[] phoneNumbers = {"15730445330"};
        aliyunSmsMessageService.sendSms(phoneNumbers, "惠游重庆", SmsEnum.LOGIN);
    }

}
