package net.firemuffin303.muffinsmcapi.impl.registration;

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

    @ApiStatus.Internal
    public static RegistryPlatformHandler getPlatformHandler() {
        return PLATFORM_HANDLER;
    }

    @FunctionalInterface
    public interface RegistryPlatformHandler {
        <T> void register(ResourceRegistry<T> registry);
    }

}
