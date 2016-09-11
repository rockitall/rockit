package com.rockit.core.utils;

/**
 * Created by Allen on 2016/9/10.
 */
public interface IDGenerator<K, T> {
    K getId(T p);
}
