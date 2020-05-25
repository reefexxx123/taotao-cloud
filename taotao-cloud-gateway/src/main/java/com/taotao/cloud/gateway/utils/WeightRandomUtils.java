/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.demog.ateway.utils
 * Date: 2020/4/27 14:11
 * Author: dengtao
 */
package com.taotao.cloud.gateway.utils;

import java.util.Map;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/4/27 14:11
 */
public class WeightRandomUtils {
    public static <T> WeightMeta<T> buildWeightMeta(final Map<T, Integer> weightMap) {
        if (weightMap.isEmpty()) {
            return null;
        }
        final int size = weightMap.size();
        Object[] nodes = new Object[size];
        int[] weights = new int[size];
        int index = 0;
        int weightAdder = 0;
        for (Map.Entry<T, Integer> each : weightMap.entrySet()) {
            nodes[index] = each.getKey();
            weights[index++] = (weightAdder = weightAdder + each.getValue());
        }
        return new WeightMeta<T>((T[]) nodes, weights);
    }
}
