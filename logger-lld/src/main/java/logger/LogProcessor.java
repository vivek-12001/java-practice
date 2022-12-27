package logger;

public abstract class LogProcessor {

    LogProcessor nextLogProcessor;

    LogProcessor(LogProcessor logProcessor) {
        this.nextLogProcessor = logProcessor;
    }

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    public void log(int logLevel, String message) {
        if (nextLogProcessor != null) {
            nextLogProcessor.log(logLevel, message);
        }
    }

}
