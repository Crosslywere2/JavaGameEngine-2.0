package com.crossly.utils;

public class TimeManager {
    private static double pastTime = 0.0;

    private static double timeDelta = 0.0;

    private static double currentTime() {
        return System.nanoTime() / 1000000000.0;
    }

    protected static void init() {
        pastTime = currentTime();
    }

    public static void tick() {
        double past = pastTime;
        double now = currentTime();
        timeDelta = now - past;
        pastTime = now;
    }

    public static double getTimeDelta() {
        return timeDelta;
    }
}
