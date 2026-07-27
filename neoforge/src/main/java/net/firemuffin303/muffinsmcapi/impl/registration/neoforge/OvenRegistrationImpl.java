package net.firemuffin303.muffinsmcapi.impl.registration.neoforge;

import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;

public class OvenRegistrationImpl {
    @org.jetbrains.annotations.ApiStatus.Internal
    public static OvenRegistration.RegistryPlatformHandler getPlatformHandler() {
        if(OvenRegistration.PLATFORM_HANDLER == null){
            OvenRegistration.setPlatformHandler(OvenRegistration::addRegistry);
        }

        return OvenRegistration.PLATFORM_HANDLER;
    }
}
