package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * UnauthorizedException
 *
 * @author dengtao
 * @date 2020/6/15 11:21
*/
@JsonSerialize(using = OauthExceptionSerializer.class)
public class UnauthorizedException extends OAuth2Exception {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unauthorized";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}
