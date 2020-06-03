package com.taotao.cloud.log.annotation;


import java.lang.annotation.*;

/**
 * 系统操作记录
 *
 * @author dengtao
 * @date 2020/6/3 13:32
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
