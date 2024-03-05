package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte byteVariable = 1;
        short shortVariable = 2;
        int  intVariable = 3;
        long longVariable = 4L;
        float floatVariable = 5.0F;
        double doubleVariable = 6.0;
        char charVariable = '7';
        boolean booleanVariable = true;

        LOG.debug("Info : {}, {}, {}, {}, {}, {}, {}, {}", byteVariable, shortVariable, intVariable,
                longVariable, floatVariable, doubleVariable, charVariable, booleanVariable);
    }
}