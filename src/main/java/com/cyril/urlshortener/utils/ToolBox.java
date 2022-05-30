package com.cyril.urlshortener.utils;

public class ToolBox {
    public static long getMinutesInLong(double minutes) {
        return (long) (minutes * 60 * 1000L);
    }
}
