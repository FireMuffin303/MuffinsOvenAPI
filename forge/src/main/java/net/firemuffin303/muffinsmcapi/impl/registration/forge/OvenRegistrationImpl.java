package net.firemuffin303.muffinsmcapi.impl.registration.forge;

import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;

public class OvenRegistrationImpl {

    public static <T> void registerRegistry(ResourceRegistry<T> registry) {
        OvenRegistration.addRegistry(registry);
    }
}
