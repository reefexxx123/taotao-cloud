package com.taotao.cloud.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Classname ValidateCodeException
 * @Description TODO
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-08-21 15:32
 * @Version 1.0
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
