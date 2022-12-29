package loadbalancer.algorithms;

import loadbalancer.IpPool;
import loadbalancer.LoadBalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoundRobin implements LoadBalance {

    private static Integer position = 0;

    @Override
    public String getServer(String clientIp) {

        String target = null;
        Set<String> ipAddressSet = IpPool.ipMapWithCapacity.keySet();
        List<String> ipAddressList = new ArrayList<>(ipAddressSet.size());
        ipAddressList.addAll(ipAddressSet);

        synchronized (position) {
            if (position > ipAddressList.size() - 1) {
                position = 0;
            }
            target = ipAddressList.get(position);
            position++;
        }
        return target;
    }
}
