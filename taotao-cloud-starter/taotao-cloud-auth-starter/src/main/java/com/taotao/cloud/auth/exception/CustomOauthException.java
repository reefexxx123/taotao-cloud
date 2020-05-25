package com.taotao.cloud.auth.exception;

import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Classname PrexCustomOauthException
 * @Description
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-09-17 23:20
 * @Version 1.0
 */
public class CustomOauthException extends OAuth2Exception {

    @Getter
    private String errorCode;

    public CustomOauthException(String msg) {
        super(msg);
    }

    public CustomOauthException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
