package net.firemuffin303.muffinsmcapi.impl.registration;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

public class OvenRegistration {
    public static RegistryPlatformHandler PLATFORM_HANDLER;

    public static final List<ResourceRegistry<?>> RESOURCE_REGISTRIES = new ArrayList<>();

    @ApiStatus.Internal
    public static void addRegistry(ResourceRegistry<?> registry){
        RESOURCE_REGISTRIES.add(registry);
    }

    @ApiStatus.Internal
    public static void setPlatformHandler(RegistryPlatformHandler handler) {
        PLATFORM_HANDLER = handler;
    }

    @ExpectPlatform
    @ApiStatus.Internal
    public static RegistryPlatformHandler getPlatformHandler() {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface RegistryPlatformHandler {
        <T> void register(ResourceRegistry<T> registry);
    }

}
