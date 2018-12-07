package org.wsr.stu.servicelocator.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshengren on 16/10/26.
 */
public class Cache {
    private List<Service> cache;

    public Cache() {
        cache = new ArrayList<Service>();
    }

    public Service getService(String serviceName) {
        for (Service item : cache) {
            if (item.getName().equalsIgnoreCase(serviceName)) {
                System.out.println("Returning cached  " + serviceName + " object");
                return item;
            }
        }
        return null;
    }

    public void addService(Service service) {
        boolean exist = false;
        if (service != null) {
            for (Service item : cache) {
                if (item.getName().equalsIgnoreCase(service.getName())) {
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            cache.add(service);
        }
    }
}
