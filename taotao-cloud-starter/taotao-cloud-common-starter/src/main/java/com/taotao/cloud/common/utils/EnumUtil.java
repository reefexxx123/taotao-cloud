package com.taotao.cloud.common.utils;

/**
 * EnumUtils
 *
 * @author dengtao
 * @date 2020/6/2 16:35
 */
public class EnumUtil {
    public static <T extends Enum<?>> T lookup(Class<T> enumType, String name) {
        for (T enumn : enumType.getEnumConstants()) {
            if (enumn.name().equalsIgnoreCase(name)) {
                return enumn;
            }
        }
        return null;
    }
}
