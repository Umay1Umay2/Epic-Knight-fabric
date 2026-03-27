package com.magistuarmory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centralized logger for Epic Knights mod.
 * Provides consistent logging across all mod components.
 */
public class EpicKnightsLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("EpicKnights");
    
    private EpicKnightsLogger() {
    }
    
    public static Logger getLogger() {
        return LOGGER;
    }
    
    public static void info(String message, Object... args) {
        LOGGER.info(message, args);
    }
    
    public static void warn(String message, Object... args) {
        LOGGER.warn(message, args);
    }
    
    public static void error(String message, Object... args) {
        LOGGER.error(message, args);
    }
    
    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
    
    public static void debug(String message, Object... args) {
        LOGGER.debug(message, args);
    }
    
    public static void trace(String message, Object... args) {
        LOGGER.trace(message, args);
    }
}
