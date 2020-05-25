/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.demo.common.log.util
 * Date: 2020/4/27 16:16
 * Author: dengtao
 */
package com.taotao.cloud.log.util;

import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.log.annotation.SysOperateLog;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

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
                    description = method.getAnnotation(SysOperateLog.class).descrption();
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
}
