package net.firemuffin303.muffinsmcapi.fabric.api;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;

public class CustomRegistryHelper {
    public final Map<ResourceKey<? extends Registry<?>>,Registry<?>> CUSTOM_REGISTRY_MAP = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> Registry<T> register(ResourceKey<Registry<T>> resourceKey, Registry<T> registry){
        return (Registry<T>) CUSTOM_REGISTRY_MAP.put(resourceKey,registry);
    }

    @SuppressWarnings("unchecked")
    public <T> Registry<T> get(ResourceKey<Registry<T>> resourceKey){
        if(BuiltInRegistries.REGISTRY.containsKey(resourceKey.location())){
            return (Registry<T>) BuiltInRegistries.REGISTRY.get(resourceKey.location());
        }

        return (Registry<T>) CUSTOM_REGISTRY_MAP.get(resourceKey);
    }
}
