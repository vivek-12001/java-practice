package loadbalancer;

import loadbalancer.algorithms.*;

public class Main {

    public static void main(String[] args) {
        getServer(new RoundRobin(), 10);
        getServer(new IpHash(), 10);
        getServer(new Random(), 10);
        getServer(new WeightRoundRobin(), 100);
        getServer(new WeightRandom(), 10);
    }

    private static void getServer(LoadBalance loadBalance, int noOfClientRequest) {

        for (int i = 0; i < noOfClientRequest; i++) {
            String serverId = loadBalance.getServer(String.valueOf(i));
            System.out.println(String.format("[%s] index:%s, ipAddress:%s", loadBalance.getClass().getSimpleName(), i, serverId));
        }
    }
}
