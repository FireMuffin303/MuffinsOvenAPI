package net.firemuffin303.muffinsmcapi.impl.registration.fabric;

import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class OvenRegistrationImpl {
    public static <T> void registerRegistry(ResourceRegistry<T> registry) {
        registry.getValues().forEach(registryHolder -> {
            Registry.register(MuffinsmcapiFabric.INSTANCE.get(registry.getResource()),registryHolder.getResourceLocation(),registryHolder.resolve());
        });
    }
}
