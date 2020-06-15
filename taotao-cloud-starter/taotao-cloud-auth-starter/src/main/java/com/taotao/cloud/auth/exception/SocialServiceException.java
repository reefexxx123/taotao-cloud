package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * SocialServiceException
 *
 * @author dengtao
 * @date 2020/6/15 11:21
*/
@JsonSerialize(using = OauthExceptionSerializer.class)
public class SocialServiceException extends OAuth2Exception {

    public SocialServiceException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "social_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 423;
    }
}
