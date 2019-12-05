package com.dmcs.blaszkub.utils;

public class TimeCounter {
    private static Long startMilis;
    private static Long endMilis;

    public static void start() {
        startMilis = System.currentTimeMillis();
    }

    public static void stop() {
        endMilis = System.currentTimeMillis();
    }

    public Long getTimeSpentInMilis() {
        return endMilis - startMilis;
    }
}
