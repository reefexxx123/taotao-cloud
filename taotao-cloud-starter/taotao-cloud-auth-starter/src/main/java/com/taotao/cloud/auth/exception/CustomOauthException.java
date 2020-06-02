package com.taotao.cloud.auth.exception;

import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * CustomOauthException
 *
 * @author dengtao
 * @date 2020/6/2 15:34
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
