package loadbalancer.algorithms;

import loadbalancer.IpPool;
import loadbalancer.LoadBalance;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class IpHash implements LoadBalance {
    @Override
    public String getServer(String clientIp) {

        String target = null;
        Set<String> ipAddressSet = IpPool.ipMapWithCapacity.keySet();
        List<String> ipAddressList = new ArrayList<>(ipAddressSet.size());
        ipAddressList.addAll(ipAddressSet);

        Integer index = clientIp.hashCode() % ipAddressList.size();
//        log.info("clientIp : {} and index : {}", clientIp, index);
        target = ipAddressList.get(index);

        return target;
    }
}
