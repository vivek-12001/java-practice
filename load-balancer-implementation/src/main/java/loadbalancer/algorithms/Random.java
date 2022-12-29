package loadbalancer.algorithms;

import loadbalancer.IpPool;
import loadbalancer.LoadBalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Random implements LoadBalance {
    @Override
    public String getServer(String clientIp) {
        String target = null;
        Set<String> ipAddressSet = IpPool.ipMapWithCapacity.keySet();
        List<String> ipAddressList = new ArrayList<>(ipAddressSet.size());
        ipAddressList.addAll(ipAddressSet);

        int index = new java.util.Random().nextInt(ipAddressList.size());
        target = ipAddressList.get(index);

        return target;
    }
}
