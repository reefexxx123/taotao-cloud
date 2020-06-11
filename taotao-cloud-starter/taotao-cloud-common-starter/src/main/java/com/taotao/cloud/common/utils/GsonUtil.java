package com.taotao.cloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

/**
 * GsonUtil
 *
 * @author dengtao
 * @date 2020/6/2 16:36
 */
@UtilityClass
public class GsonUtil {

    public String toGson(Object src) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(src);
    }
}