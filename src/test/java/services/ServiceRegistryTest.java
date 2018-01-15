package services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;


import static org.junit.Assert.assertEquals;

public class ServiceRegistryTest {

    @After
    public void setup() {
        Map<String, Service> customHM = new HashMap<>();
        customHM.putAll(ServiceRegistry.getInstance().getServices());
        for(Service s : customHM.values()) {
            ServiceRegistry.getInstance().getServices().remove(s.getName(),s);
        }
        assertEquals(0, ServiceRegistry.getInstance().getServices().size());
    }

    @Test
    public void serviceRegistryShouldBeInitializeOnlyOnce() {
        ServiceRegistry sr = ServiceRegistry.getInstance();
        ServiceRegistry sr2 = ServiceRegistry.getInstance();

        assertSame(sr, sr2);
    }

    @Test
    public void serviceShouldBeAdded() {
        UserService userService = new MockUserService();
        ServiceRegistry.getInstance().addService(userService);

        assertEquals(1, ServiceRegistry.getInstance().getServices().size());
    }

    @Test
    public void servicesShouldBeReturned() {
        UserService userService = new MockUserService();
        BootcampService bService = new JPABootcampService();
        ServiceRegistry.getInstance().addService(userService);
        ServiceRegistry.getInstance().addService(bService);

        assertSame(userService, ServiceRegistry.getInstance().getService(userService.getName()));
        assertSame(bService, ServiceRegistry.getInstance().getService(bService.getName()));
    }
}
