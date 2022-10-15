package com.frc5113.logging;

import java.util.ArrayList;

import com.frc5113.library.primative.BaseLoggerFlags;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * Base logic used to send logging messages to logging location.
 * You can turn off all debug messages easily. You can also set different Flag levels.
 * @author Simbiotics (1114, Y2015), Spectrum (3847), Vladimir Bondar (5113)
 */
abstract public class BaseLogger {
    public static final int low1 = 1;
    public static final int normal2 = 2;
    public static final int high3 = 3;
    public static final int critical4 = 4;
    public static final int silent5 = 5; // Nothing is printed

    public enum Level {
        Low(1),
        Normal(2),
        High(3),
        Critical(4),
        Silent(5);

        public final int level;

        Level(int level) {
            this.level = level;
        }
    }

    private static final ArrayList<String> currFlags;
    private static boolean defaultOn;
    private static Level currLevel;

    static {
        currFlags = new ArrayList<>();
        currLevel = Level.Low;
        defaultOn = false;
    }

    public static void println(String msg) {
        if(defaultOn) {
            System.out.println("[DEBUG] " + msg);
        }
    }

    /**
     * Check if we should print the message based on flag and level
     * Print the level and flag as well
     * If the level is error or above also send it to DS output
     * @param msg String message
     * @param flag The subsystem responsible
     * @param level The severity
     */
    public static void println(String msg, String flag, Level level) {
        //Log events to both system.out and Shuffleboard Event Markers
        if(meetsCurrRequirements(flag, level)) {
            System.out.println(level + ": [" + flag + "] " + msg);
            Shuffleboard.addEventMarker(flag + ":  " + msg, logLevelToEventImportance(level));
        }
        // Critical are sent to the Driver station no matter what the logger level is set to
        if (level == Level.Critical){
            DriverStation.reportWarning(flag + ":  " + msg, false);
        }
    }

    public static void println(String msg, BaseLoggerFlags flag, Level level) {
        println(msg, flag.tag, level);
    }

    public static void println(String msg, String flag) {
        println(msg, flag, Level.Low);
    }

    public static void println(int msg) {
        println("" + msg);
    }

    public static void println(int msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(int msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(double msg) {
        println("" + msg);
    }

    public static void println(double msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(double msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(float msg) {
        println("" + msg);
    }

    public static void println(float msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(float msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(long msg) {
        println("" + msg);
    }

    public static void println(long msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(long msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(boolean msg) {
        println("" + msg);
    }

    public static void println(boolean msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(boolean msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(Object msg) {
        println(msg.toString());
    }

    public static void println(Object msg, String flag) {
        println(msg.toString(), flag);
    }

    public static void println(Object msg, String flag, Level level) {
        println(msg.toString(), flag, level);
    }

    public static void println(byte msg) {
        println("" + msg);
    }

    public static void println(byte msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(byte msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(char msg) {
        println("" + msg);
    }

    public static void println(char msg, String flag) {
        println("" + msg, flag);
    }

    public static void println(char msg, String flag, Level level) {
        println("" + msg, flag, level);
    }

    public static void println(char[] msg) {
        println(new String(msg));
    }

    public static void println(char[] msg, String flag) {
        println(new String(msg), flag);
    }

    public static void println(char[] msg, String flag, Level level) {
        println(new String(msg), flag, level);
    }

    public static void flagOn(String flag) {
        if(!currFlags.contains(flag)) {
            currFlags.add(flag);
        }
    }

    public static void flagOff(String flag) {
        currFlags.remove(flag);
    }

    public static void allFlagsOff() {
        currFlags.clear();
    }

    public static void defaultOn() {
        defaultOn = true;
    }

    public static void defaultOff() {
        defaultOn = false;
    }

    public static void setLevel(Level level) {
        currLevel = level;
    }

    //Check if the flag is set and if the level is high enough to print
    private static boolean meetsCurrRequirements(String flag, Level level) {
        for (String currFlag : currFlags) {
            if (currFlag.equals(flag) && level.level >= currLevel.level) {
                return true;
            }
        }
        return false;
    }

    // Log Level to Shuffleboard Event Importance converter
    private static EventImportance logLevelToEventImportance(Level level){
        switch(level){
            case Low: return EventImportance.kLow;
            case Normal: return EventImportance.kNormal;
            case High: return EventImportance.kHigh;
            case Critical: return EventImportance.kCritical;
            default: return EventImportance.kTrivial;
        }
    }
}
