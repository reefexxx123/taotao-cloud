package com.taotao.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * LogUtils
 *
 * @author dengtao
 * @date 2020/6/2 16:36
 */
@Slf4j
public class LogUtil {

    public static boolean isDebugEnabled() {
        return true;
    }

    public static boolean isInfoEnabled() {
        return true;
    }

    public static boolean isErrorEnabled() {
        return true;
    }

    public static boolean isWarnEnabled() {
        return true;
    }

    public static void info(Class cls, String project, String msg) {
        if (isInfoEnabled() && log.isInfoEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.info("[TAOTAO CLOUD][" + project + "]" + msg);
        }
    }

    public static void debug(Class cls, String project, String msg) {
        if (isDebugEnabled() && log.isDebugEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.debug("[TAOTAO CLOUD][" + project + "]" + msg);
        }
    }

    public static void error(Class cls, String project, String msg, Throwable exp) {
        if (isErrorEnabled() && log.isErrorEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.error("[TAOTAO CLOUD][" + project + "]" + msg, exp);
        }
    }

    public static void warn(Class cls, String project, String msg) {
        if (isWarnEnabled() && log.isWarnEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.warn("[TAOTAO CLOUD][" + project + "]" + msg);
        }
    }

    public static void warn(Class cls, String project, String msg, Throwable exp) {
        if (isWarnEnabled() && log.isWarnEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.warn("[TAOTAO CLOUD][" + project + "]" + msg, exp);
        }
    }
}
