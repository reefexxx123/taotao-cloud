package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * OAuth2Exception
 *
 * @author dengtao
 * @date 2020/6/2 15:34
 */
@JsonSerialize(using = OauthExceptionSerializer.class)
public class InvalidException extends OAuth2Exception {

    public InvalidException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 422;
    }
}
