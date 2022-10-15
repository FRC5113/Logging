package com.frc5113.logging;

import com.frc5113.library.primative.BaseLoggerFlags;
import com.frc5113.logging.BaseLogger.Level;

public class DataLogger {
    public static void printLow(BaseLoggerFlags tag, String msg) {
        FlagLogger.println(msg, tag, Level.Low);
    }

    public static void printNormal(BaseLoggerFlags tag, String msg) {
        FlagLogger.println(msg, tag, Level.Normal);
    }

    public static void printHigh(BaseLoggerFlags tag, String msg) {
        FlagLogger.println(msg, tag, Level.High);
    }
}
