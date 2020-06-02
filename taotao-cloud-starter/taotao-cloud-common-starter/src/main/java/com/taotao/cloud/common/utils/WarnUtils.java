package com.taotao.cloud.common.utils;

import lombok.var;

/**
 * 报警工具类
 *
 * @author dengtao
 * @date 2020/4/17 15:43
 */
public class WarnUtils {
    //错误报警
    public static final String ALARM_ERROR = "ERROR";
    //警告
    public static final String ALARM_WARN = "WARN";
    //通知
    public static final String ALARM_INFO = "INFO";

    /**
     * 即时发送报警通知
     *
     * @param alarmType 报警类型
     * @param title     报警标题
     * @param content   报警内容
     */
    public static void notifynow(String alarmType, String title, String content) {
        notify(alarmType, title, content, true);
    }

    /**
     * 发送报警
     *
     * @param alarmType 告警类型
     * @param title     告警标题
     * @param content   告警内容
     */
    public static void notify(String alarmType, String title, String content) {
        notify(alarmType, title, content, false);
    }

    /**
     * 发送报警
     *
     * @param alarmType 告警类型
     * @param title     告警标题
     * @param content   告警内容
     * @param isNow     是否即时发送
     */
    public static void notify(String alarmType, String title, String content, boolean isNow) {
        Class clazz = ReflectionUtil.classForName("com.yh.csx.bsf.health.warn.WarnProvider");
        if (clazz != null) {
            var bean = ContextUtil.getBean(clazz, false);
            if (bean != null) {
                if (isNow) {
                    ReflectionUtil.callMethodWithParams(bean, "notifynow", new String[]{alarmType, title, content}, String.class, String.class, String.class);
                } else {
                    ReflectionUtil.callMethodWithParams(bean, "notify", new String[]{alarmType, title, content}, String.class, String.class, String.class);
                }
            }
        }
    }

}
