package com.taotao.cloud.log.annotation;


import java.lang.annotation.*;

/**
 * @author dengtao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SysOperateLog {

    /**
     * 功能描述
     */
    String description() default "";
}
