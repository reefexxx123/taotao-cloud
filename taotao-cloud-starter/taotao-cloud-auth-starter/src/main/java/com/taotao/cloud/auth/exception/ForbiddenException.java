package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * ForbiddenException
 *
 * @author dengtao
 * @date 2020/6/2 15:35
 */
@JsonSerialize(using = OauthExceptionSerializer.class)
public class ForbiddenException extends OAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }

}
