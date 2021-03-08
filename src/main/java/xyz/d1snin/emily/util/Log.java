package xyz.d1snin.emily.util;

import java.time.LocalTime;

public class Log {
    public static void Info(String text) {
        System.out.println("[" + getTime() + "]" + "[INFO] - " + text);
    }
    public static void Warn(String text) {
        System.out.println("[" + getTime() + "]" + "[WARN] - " + text);
    }
    public static void Error(String text) {
        System.out.println("[" + getTime() + "]" + "[ERROR] - " + text);
    }
    private static String getTime() {
        LocalTime now = LocalTime.now();
        String time = now.toString();
        return time.substring(0, 8);
    }
}
