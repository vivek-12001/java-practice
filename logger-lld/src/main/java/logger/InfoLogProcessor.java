package logger;

public class InfoLogProcessor extends LogProcessor {

    InfoLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if (logLevel == 1) {
            System.out.println("INFO LOGGER" + message);
        } else {
            super.log(logLevel, message);
        }
    }
}
