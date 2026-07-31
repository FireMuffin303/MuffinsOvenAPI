package net.firemuffin303.muffinsmcapi.impl.registration.fabric;

import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class OvenRegistrationImpl {
    @org.jetbrains.annotations.ApiStatus.Internal
    public static OvenRegistration.RegistryPlatformHandler getPlatformHandler() {
        if(OvenRegistration.PLATFORM_HANDLER == null){
            OvenRegistration.setPlatformHandler(new OvenRegistration.RegistryPlatformHandler() {
                @Override
                public <T> void register(ResourceRegistry<T> registry) {
                    registry.getValues().forEach((holder,supplier) -> Registry.register(MuffinsmcapiFabric.INSTANCE.get(registry.getResource()),holder.getResourceLocation(),supplier.get()));
                }
            });
        }

        return OvenRegistration.PLATFORM_HANDLER;
    }
}
