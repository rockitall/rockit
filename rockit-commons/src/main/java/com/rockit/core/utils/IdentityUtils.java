package com.rockit.core.utils;

import java.util.*;

/**
 * Created by Allen on 2016/9/10.
 */
public class IdentityUtils {
    public static <K, V> List<K> collectIdList(List<V> objects, IDGenerator<K, V> generator) {
        List<K> idList = new ArrayList<>(objects.size());
        objects.stream().forEach(v -> idList.add(generator.getId(v)));
        return idList;
    }

    public static <K, V> Map<K, V> asMap(Collection<V> values, IDGenerator<K, V> generator) {
        Map<K, V> map = new HashMap<>();
        if (values != null && values.size() > 0) {
            values.stream().forEach(v -> map.put(generator.getId(v), v));
        }
        return map;
    }
}
