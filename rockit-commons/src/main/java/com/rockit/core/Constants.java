package com.rockit.core;

/**
 * Created by Allen on 2016/8/1.
 */
public interface Constants {
    interface BOOL {
        byte YES = 1;
        byte NO = 0;

        static boolean isTrue(Byte val) {
            return val != null && YES == val;
        }
    }
}
