package com.taotao.cloud.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taotao.cloud.auth.serializer.CustomOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Classname SocialServiceException
 * @Description
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-09-17 23:25
 * @Version 1.0
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
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
