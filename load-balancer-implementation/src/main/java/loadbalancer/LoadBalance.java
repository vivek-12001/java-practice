package loadbalancer;

public interface LoadBalance {

    String getServer(String clientIp);
}
