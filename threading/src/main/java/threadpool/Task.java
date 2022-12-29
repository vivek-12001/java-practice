package threadpool;

import lombok.SneakyThrows;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.Callable;

public class Task implements Callable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(2000);
        return name;
    }
}
