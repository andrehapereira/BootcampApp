package services;

import java.util.HashMap;
import java.util.Map;

public final class ServiceRegistry {
    private static ServiceRegistry serviceRegistry = null;
    private Map<String, Service> services;

    private ServiceRegistry() {
        services = new HashMap<>();
    }

    public static synchronized ServiceRegistry getInstance() {
        if(serviceRegistry == null) {
            serviceRegistry = new ServiceRegistry();
        }
        return serviceRegistry;
    }

    public void addService(Service value) {
        services.put(value.getName(),value);
    }

    public Service getService(String srvName) {
        return services.get(srvName);
    }


    public void removeService(Service service) {
        services.remove(service.getName(),service);
    }

    public Map<String, Service> getServices() {
        return services;
    }

    public void setServices(Map<String, Service> services) {
        this.services = services;
    }
}
