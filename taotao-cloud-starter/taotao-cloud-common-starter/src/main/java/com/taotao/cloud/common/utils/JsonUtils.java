package com.taotao.cloud.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.taotao.cloud.common.serialize.JsonSerializer;

import java.lang.reflect.Type;

/**
 * JsonUtils
 *
 * @author: dengtao
 * @version: 2019-07-28 14:59
 **/
public class JsonUtils {
    public static JsonSerializer Default = new JsonSerializer();

    public static String serialize(Object object) {
        return Default.serialize(object);
    }

    public static <T> T deserialize(String str, Type type) {
        return Default.deserialize(str, type);
    }

    public static <T> T deserialize(String str, TypeReference<T> type) {
        return Default.deserialize(str, type.getType());
    }
}
