package logger;

public class Application {

    public static void main(String[] args) {
        LogProcessor logProcessor = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logProcessor.log(LogProcessor.INFO, "will be invoked");
        logProcessor.log(LogProcessor.DEBUG, "will be invoked");
        logProcessor.log(LogProcessor.ERROR, "will be invoked");
    }
}
