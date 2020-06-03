package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.OauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Classname InvalidGrantException
 * @Description
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-09-17 21:10
 * @Version 1.0
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
