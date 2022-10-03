import java.util.function.Function;

public class Singleton {

    /*private static Singleton singletonInstance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Singleton();
        }
        return singletonInstance;
    }*/

/*
    private static volatile Singleton singletonInstance = null;
*/

    private Singleton() {
        System.out.println("Instance created");
        /*if (singletonInstance != null) {
            throw new RuntimeException("Instance creation exception");
        }*/
    }

    /*public static Singleton getInstance() {
        if (singletonInstance == null) {
            synchronized (Singleton.class) {
                if (singletonInstance == null) {
                    singletonInstance = new Singleton();
                }
            }
        }
        return singletonInstance;
    }*/

    private static  class SingletonHelper {
        private static final Singleton singletonInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.singletonInstance;
    }

    /**
     * this method gets invoke when desearilization happens
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

}
