package logger;

public class DebugLogProcessor extends LogProcessor {

    DebugLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if (logLevel == 2) {
            System.out.println("Debug LOGGER" + message);
        } else {
            super.log(logLevel, message);
        }
    }
}
