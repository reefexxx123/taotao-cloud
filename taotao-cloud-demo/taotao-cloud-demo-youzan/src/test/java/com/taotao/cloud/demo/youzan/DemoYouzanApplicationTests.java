package com.taotao.cloud.demo.youzan;

import com.taotao.cloud.demo.youzan.youzan.YouzanTest;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoYouzanApplicationTests {

    @Autowired
    private YouzanTest youzanTest;

    @Test
    void contextLoads() throws SDKException {
        youzanTest.getYouZanUserInfo();;
    }

}
