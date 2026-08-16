package net.firemuffin303.muffinsmcapi.impl.registration.fabric;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public class OvenRegistrationImpl {
    @org.jetbrains.annotations.ApiStatus.Internal
    public static <T> void registerRegistry(ResourceRegistry<T> registry) {
        registry.getValues().forEach((holder, supplier) -> {
            Registry.register(MuffinsmcapiFabric.INSTANCE.get(registry.getResource()),holder.getResourceLocation(),supplier.get());
            holder.createHolder(false);
        });
    }

    public static <T> Registry<T> buildRegistry(ResourceKey<Registry<T>> resourceKey) {
        Registry<T> registry = FabricRegistryBuilder.createSimple(resourceKey).attribute(RegistryAttribute.SYNCED).buildAndRegister();
        MuffinsmcapiFabric.INSTANCE.register(resourceKey,registry);
        return registry;
    }

    public static <T> void fabricRegister(RegistryHolder<T> registryHolder,Supplier<T> object) {
        /*
        Registry<T> registry = MuffinsmcapiFabric.INSTANCE.get(registryHolder.getResourceKey());
        Registry.register(registry,registryHolder.getResourceLocation(),object.get());
        registryHolder.createHolder(false);

         */
    }
}
