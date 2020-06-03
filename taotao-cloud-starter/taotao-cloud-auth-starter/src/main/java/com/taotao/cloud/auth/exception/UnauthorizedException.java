package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Classname UnauthorizedException
 * @Description
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-09-17 20:48
 * @Version 1.0
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
