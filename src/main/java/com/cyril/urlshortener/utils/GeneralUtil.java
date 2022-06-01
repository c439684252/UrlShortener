package com.cyril.urlshortener.utils;

public class GeneralUtil {
    public static long transMinute2Millisecond(double minutes) {
        return (long) (minutes * 60 * 1000L);
    }

    public static int transDay2Minute(int days) {
        return days * 24 * 60;
    }
}
