package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();

        for (int i=0;i<5;i++) {
            Task task = new Task("Task" + i);
            Future<String> futures = executorService.submit(task);
            futureList.add(futures);
        }

        for (Future<String> futures : futureList) {
            try {
                System.out.println("futures = " + futures.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        shutdownAndAwaitTermination(executorService);
    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {

        // prevents new task getting submitted
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executorService.shutdownNow();
        }
    }
}


