package net.firemuffin303.muffinsmcapi.impl.registration;

import net.minecraft.resources.ResourceKey;

public class OvenRegistry<T> {
    private final ResourceKey<T> resourceKey;

    public OvenRegistry(ResourceKey<T> resourceKey){
        this.resourceKey = resourceKey;
    }

    public OvenRegistry<T> create(ResourceKey<T> resourceKey){
        return new OvenRegistry<>(resourceKey);
    }
}
