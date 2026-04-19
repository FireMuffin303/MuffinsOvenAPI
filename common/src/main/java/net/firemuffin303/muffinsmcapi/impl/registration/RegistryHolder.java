package net.firemuffin303.muffinsmcapi.impl.registration;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class RegistryHolder<T> {
    private final ResourceLocation resourceLocation;
    private final Supplier<T> object;
    private T resolvedObject;

    public RegistryHolder(ResourceLocation resourceLocation,Supplier<T> supplier){
        this.resourceLocation = resourceLocation;
        this.object = supplier;
    }

    public T resolve() {
        if (this.resolvedObject == null) this.resolvedObject = this.object.get();
        return resolvedObject;
    }

    public T get(){
        return this.resolvedObject;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }
}
