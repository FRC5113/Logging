package com.frc5113.logging;

import com.frc5113.library.primative.BaseLoggerFlags;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class adds robot specific flags to Logger class
 */
public class FlagLogger extends BaseLogger {
    private static Level FMS_LOG_LEVEL;        //Log level when FMS attached
    private static Level PRACTICE_LOG_LEVEL;    // Log level any other time
    private static BaseLoggerFlags flags;

    /**
     * Create a default logger with the parameters:
     * FMS - High
     * Practice - Low
     */
    public FlagLogger(BaseLoggerFlags flags) {
        FMS_LOG_LEVEL = Level.High;
        PRACTICE_LOG_LEVEL = Level.Low;
        FlagLogger.flags = flags;
    }

    /**
     * Create a logger with different parameters
     * @param FMS Log level when connected to FMS
     * @param practice Log level when not connected to FMS
     */
    public FlagLogger(BaseLoggerFlags flags, Level FMS, Level practice) {
        FMS_LOG_LEVEL = FMS;
        PRACTICE_LOG_LEVEL = practice;
        FlagLogger.flags = flags;
    }

    public static void initLog(){
        if(DriverStation.isFMSAttached()) {
            setLevel(FMS_LOG_LEVEL);
        } else {
            setLevel(PRACTICE_LOG_LEVEL);
        }
        //Set all the flags on, make sure to define it in your enum definition
        for (BaseLoggerFlags b : FlagLogger.flags.getValues()) {
            flagOn(b.tag);
        }
    }
}
