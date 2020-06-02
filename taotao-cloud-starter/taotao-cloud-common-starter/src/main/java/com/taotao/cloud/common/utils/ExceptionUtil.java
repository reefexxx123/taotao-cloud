package com.taotao.cloud.common.utils;


import com.taotao.cloud.common.exception.BaseException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ExceptionUtils
 *
 * @author: dengtao
 * @version: 2019-07-28 11:08
 **/
public class ExceptionUtil {
    public static String trace2String(Throwable t) {
        if (t == null) {
            return "";
        }
        try {
            try (StringWriter sw = new StringWriter()) {
                try (PrintWriter pw = new PrintWriter(sw, true)) {
                    t.printStackTrace(pw);
                    return sw.getBuffer().toString();
                }
            }
        } catch (Exception exp) {
            throw new BaseException(exp.getMessage());
        }
    }

    public static String trace2String(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElemen : stackTraceElements) {
            sb.append(stackTraceElemen.toString() + "\n");
        }
        return sb.toString();
    }
}
