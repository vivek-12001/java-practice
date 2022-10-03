import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        Singleton singletonObj1 = Singleton.getInstance();
        System.out.println(singletonObj1);

        Singleton singletonObj2 = Singleton.getInstance();
        System.out.println(singletonObj2);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());
        executorService.execute(() -> Singleton.getInstance());

    }

}
