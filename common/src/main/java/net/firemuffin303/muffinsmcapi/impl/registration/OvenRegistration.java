package net.firemuffin303.muffinsmcapi.impl.registration;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

public class OvenRegistration {

    public static final List<ResourceRegistry<?>> RESOURCE_REGISTRIES = new ArrayList<>();

    @ApiStatus.Internal
    public static void addRegistry(ResourceRegistry<?> registry){
        RESOURCE_REGISTRIES.add(registry);
    }

    @ExpectPlatform
    public static <T> void registerRegistry(ResourceRegistry<T> registry){
        throw new AssertionError();
    }

}
