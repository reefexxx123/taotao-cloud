/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.demo.common.log.util
 * Date: 2020/4/27 16:16
 * Author: dengtao
 */
package com.taotao.cloud.log.utils;

import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.log.annotation.SysOperateLog;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * LogUtil
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/4/27 16:16
 */
@UtilityClass
public class LogUtil {
    /**
     * 空数组
     */
    private final Object[] EMPTY_ARRAY = new Object[]{};
    /**
     * 全类名
     */
    private final String FQCN = LogUtil.class.getName();


    /**
     * 获取栈中类信息
     *
     * @param stackDepth
     * @return
     */
    private LocationAwareLogger getLocationAwareLogger(final int stackDepth) {
        /**通过堆栈信息获取调用当前方法的类名和方法名*/
        JavaLangAccess access = SharedSecrets.getJavaLangAccess();
        Throwable throwable = new Throwable();
        StackTraceElement frame = access.getStackTraceElement(throwable, stackDepth);
        return (LocationAwareLogger) LoggerFactory.getLogger(frame.getClassName() + "-" +
                frame.getMethodName().split("\\$")[0] + "-" +
                frame.getLineNumber());

    }

    /**
     * 封装Debug级别日志
     *
     * @param msg
     * @param arguments
     */
    public void debug(String msg, Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);
    }

    /**
     * 封装Info级别日志
     *
     * @param msg
     * @param arguments
     */
    public void info(String msg, Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);
    }

    /**
     * 封装Warn级别日志
     *
     * @param msg
     * @param arguments
     */
    public void warn(String msg, Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);
    }

    /**
     * 封装Error级别日志
     *
     * @param msg
     * @param arguments
     */
    public void error(String msg, Throwable error, Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, error);
    }

    /**
     * 异常堆栈转字符串
     *
     * @param e
     * @return
     */
    public String ExceptionToString(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            if (e == null) {
                return "无具体异常信息";
            }
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception ex) {
            return "";
        } finally {
            sw.flush();
            pw.flush();
            pw.close();
        }
    }

    /**
     * 获取操作信息
     *
     * @param point point
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/30 10:21
     */
    public String getControllerMethodDescription(JoinPoint point) throws Exception {
        // 获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SysOperateLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/30 10:22
     */
    public String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    /**
     * 获取操作类型
     *
     * @param methodName
     * @return int
     * @author dengtao
     * @date 2020/4/30 10:22
     */
    public int getOperateType(String methodName) {
        if (methodName.startsWith("get")) {
            return CommonConstant.OPERATE_TYPE_1;
        }
        if (methodName.startsWith("add")) {
            return CommonConstant.OPERATE_TYPE_2;
        }
        if (methodName.startsWith("update")) {
            return CommonConstant.OPERATE_TYPE_3;
        }
        if (methodName.startsWith("delete")) {
            return CommonConstant.OPERATE_TYPE_4;
        }
        return CommonConstant.OPERATE_TYPE_1;
    }

    public void info(String project, String msg) {
        info("[TAOTAO CLOUD][" + project + "]" + msg);
    }

    public void debug(String project, String msg) {
        debug("[TAOTAO CLOUD][" + project + "]" + msg);
    }

    public void error(String project, String msg, Throwable exp) {
        error("[TAOTAO CLOUD][" + project + "]" + msg, exp);
    }

    public void warn(String project, String msg) {
        warn("[TAOTAO CLOUD][" + project + "]" + msg);
    }
}
