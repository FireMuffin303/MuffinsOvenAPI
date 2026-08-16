package net.firemuffin303.muffinsmcapi.impl.registration.forge;

import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class OvenRegistrationImpl {
    @org.jetbrains.annotations.ApiStatus.Internal
    public static <T> void registerRegistry(ResourceRegistry<T> registry) {
        OvenRegistration.addRegistry(registry);
    }

    public static <T> Registry<T> buildRegistry(ResourceKey<Registry<T>> resourceKey) {
        return new RegistryBuilder<>(resourceKey).maxId(2048).create();
    }

    public static <T> void fabricRegister(RegistryHolder<T> registryHolder, Supplier<T> object) {
    }
}
