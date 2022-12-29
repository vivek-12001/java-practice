package loadbalancer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpPool {

    public static Map<String, Integer> ipMapWithCapacity = new ConcurrentHashMap<>();

    static {
        ipMapWithCapacity.put("196.169.05.1", 10);
        ipMapWithCapacity.put("196.169.05.2", 10);
        /*ipMapWithCapacity.put("196.169.05.3", 10);
        ipMapWithCapacity.put("196.169.05.4", 10);
        ipMapWithCapacity.put("196.169.05.5", 10);
        ipMapWithCapacity.put("196.169.05.6", 10);
        ipMapWithCapacity.put("196.169.05.7", 10);*/
    }

}
