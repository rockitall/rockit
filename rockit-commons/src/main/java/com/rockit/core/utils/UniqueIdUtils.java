package com.rockit.core.utils;

import java.util.Random;

public class UniqueIdUtils {
    private static final char[] HEX_DATA = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] DIGITAL_DATA = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static String getSalt() {
        return getRandonHex(HEX_DATA, 8);
    }

    public static String getVerifyCode() {
        return getRandonHex(DIGITAL_DATA, 6);
    }

    public static String getSSID() {
        return getRandonHex(HEX_DATA, 48);
    }

    public static String getUserSecret() {
        return getRandonHex(HEX_DATA, 32);
    }

    public static String getRandonHex(int len) {
        return getRandonHex(HEX_DATA, len);
    }

    private static String getRandonHex(char[] arr, int len) {
        Random r = new Random();
        char[] data = new char[len];
        int k = 0;
        for (int i = 0; i < len; i++) {
            k = r.nextInt(arr.length);
            data[i] = arr[k];
        }
        return new String(data);
    }
}
