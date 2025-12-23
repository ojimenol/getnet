package com.pagonxt.core.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMessage {

    private LogMessage(){}

    public static void infoGeneric(Object x, Class<?> loggerClass) {
        Logger logger = LoggerFactory.getLogger(loggerClass);
        logger.info(String.valueOf(x));
    }
}
