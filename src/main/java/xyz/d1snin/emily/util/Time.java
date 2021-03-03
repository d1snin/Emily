package xyz.d1snin.emily.util;

import java.time.LocalTime;

public class Time {
    public static String getTime() {
        LocalTime now = LocalTime.now();
        String time = now.toString();
        return time.substring(0, 8);
    }
}
