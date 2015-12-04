package ro.ubbcluj.cs.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimingLogger
{
    private final String operation;
    private Log log = LogFactory.getLog(TimingLogger.class);

    private final long startTimeMillis;

    public TimingLogger(Class<?> clazz, String operation)
    {
        log = LogFactory.getLog(clazz.getSimpleName());
        this.operation = operation;
        startTimeMillis = System.currentTimeMillis();
    }

    public void dumpToLog()
    {
        log.info(String.format("%s %d millis", operation, System.currentTimeMillis() - startTimeMillis));
    }
}
