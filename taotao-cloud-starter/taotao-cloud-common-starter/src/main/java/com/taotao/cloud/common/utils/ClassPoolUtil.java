package com.taotao.cloud.common.utils;

import javassist.ClassPool;
import javassist.LoaderClassPath;

/**
 * ClassPoolUtils
 *
 * @author dengtao
 * @date 2020/6/2 16:33
 */
public class ClassPoolUtil {

    private static volatile ClassPool instance;

    private ClassPoolUtil() {
    }

    public static ClassPool getInstance() {
        if (instance == null) {
            synchronized (ClassPoolUtil.class) {
                if (instance == null) {
                    instance = ClassPool.getDefault();
                    instance.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
                }
            }
        }
        return instance;
    }
}
