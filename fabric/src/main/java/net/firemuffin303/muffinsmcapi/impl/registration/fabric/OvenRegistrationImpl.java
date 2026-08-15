package net.firemuffin303.muffinsmcapi.impl.registration.fabric;

import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.fabric.MuffinsmcapiFabric;
import net.firemuffin303.muffinsmcapi.fabric.api.CustomRegistryHelper;
import net.firemuffin303.muffinsmcapi.impl.registration.OvenRegistration;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

public class OvenRegistrationImpl {
    @org.jetbrains.annotations.ApiStatus.Internal
    public static <T> void registerRegistry(ResourceRegistry<T> registry) {
        registry.getValues().forEach((holder, supplier) -> {
            Registry.register(MuffinsmcapiFabric.INSTANCE.get(registry.getResource()),holder.getResourceLocation(),supplier.get());
            holder.createHolder(false);
        });
    }

    public static <T> Registry<T> buildRegistry(ResourceKey<Registry<T>> resourceKey) {
        return FabricRegistryBuilder.createSimple(resourceKey).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    }
}
