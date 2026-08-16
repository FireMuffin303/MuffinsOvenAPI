package net.firemuffin303.muffinsmcapi.impl.registration;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OvenRegistration {
    public static final List<ResourceRegistry<?>> RESOURCE_REGISTRIES = new ArrayList<>();
    public static final List<Registry<?>> REGISTRY_LIST = new ArrayList<>();

    public static <T> Registry<T> createRegistry(ResourceKey<Registry<T>> resourceKey){
        Registry<T> registry = buildRegistry(resourceKey);
        REGISTRY_LIST.add(registry);
        return registry;
    }

    @ExpectPlatform
    public static <T> Registry<T> buildRegistry(ResourceKey<Registry<T>> resourceKey){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T> void registerRegistry(ResourceRegistry<T> registry){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T> void fabricRegister(RegistryHolder<T> registryHolder,Supplier<T> object){
        throw new AssertionError();
    }


    @ApiStatus.Internal
    public static void addRegistry(ResourceRegistry<?> registry){
        RESOURCE_REGISTRIES.add(registry);
    }

}
