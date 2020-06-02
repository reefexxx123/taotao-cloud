package com.taotao.cloud.common.utils;

import java.math.BigDecimal;

/**
 * NumberUtils
 *
 * @author dengtao
 * @date 2020/6/2 16:39
 */
public class NumberUtil {
    public static double scale(Number number, int scale) {
        BigDecimal bg = new BigDecimal(number.doubleValue());
        return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
