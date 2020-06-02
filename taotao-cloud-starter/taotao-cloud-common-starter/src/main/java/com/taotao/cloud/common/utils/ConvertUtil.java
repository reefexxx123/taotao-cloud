package com.taotao.cloud.common.utils;

import com.taotao.cloud.common.exception.BaseException;
import org.springframework.boot.convert.ApplicationConversionService;

import java.io.*;

/**
 * ConvertUtils
 *
 * @author dengtao
 * @date 2020/6/2 16:34
*/
public class ConvertUtil {
    public static <T> T convert(Object value, Class<T> type) {
        if (value == null) {
            return null;
        }
        return (T) ApplicationConversionService.getSharedInstance().convert(value, type);
    }

    public static <T> T tryConvert(Object value, Class<T> type) {
        try {
            return convert(value, type);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T deepClone(T obj) {
        try {
            try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
                try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
                    out.writeObject(obj);
                    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray())) {
                        ObjectInputStream in = new ObjectInputStream(byteIn);
                        return (T) in.readObject();
                    }
                }
            }
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }
}
