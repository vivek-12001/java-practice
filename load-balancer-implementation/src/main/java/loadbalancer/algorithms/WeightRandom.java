package loadbalancer.algorithms;

import loadbalancer.IpPool;
import loadbalancer.LoadBalance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WeightRandom implements LoadBalance {
    @Override
    public String getServer(String clientIp) {
        String target = null;
        Set<String> ipAddressSet = IpPool.ipMapWithCapacity.keySet();
        List<String> ipAddressList = new ArrayList<>(ipAddressSet.size());
        ipAddressList.addAll(ipAddressSet);

        Iterator<String> iterator = ipAddressSet.iterator();

        while (iterator.hasNext()) {
            String serverItem = iterator.next();
            Integer serverWeight = IpPool.ipMapWithCapacity.get(serverItem);

            if (serverWeight > 0) {
                for (int i = 0; i < serverWeight; i++) {
                    ipAddressList.add(serverItem);
                }
            }
        }

        int index = new java.util.Random().nextInt(ipAddressList.size());
        target = ipAddressList.get(index);

        return target;
    }
}
