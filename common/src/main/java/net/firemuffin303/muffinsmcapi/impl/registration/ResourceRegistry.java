package net.firemuffin303.muffinsmcapi.impl.registration;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ResourceRegistry<T> {
    private final ResourceKey<Registry<T>> resource;
    private final String modId;
    private final List<RegistryHolder<T>> values = new ArrayList<>();

    private ResourceRegistry(ResourceKey<Registry<T>> resourceKey,String modId){
        this.resource = resourceKey;
        this.modId = modId;
    }

    public static <T> ResourceRegistry<T> create(ResourceKey<Registry<T>> resourceKey, String modId){
        return new ResourceRegistry<>(resourceKey,modId);
    }

    public Supplier<T> register(String id,Supplier<T> object){
        return this.register(new ResourceLocation(this.modId,id),object);
    }

    public Supplier<T> register(ResourceLocation resourceLocation, Supplier<T> object){
        RegistryHolder<T> registryHolder = new RegistryHolder<>(resourceLocation,object);
        this.values.add(registryHolder);
        return registryHolder::get;
    }


    public ResourceKey<Registry<T>> getResource() {
        return resource;
    }

    public List<RegistryHolder<T>> getValues() {
        return values;
    }

    public void init() {
        OvenRegistration.getPlatformHandler().register(this);
    }



    @ApiStatus.Internal
    @SuppressWarnings("unchecked")
    public  <T> Registry<T> resolveRegistry() {
        return (Registry<T>) BuiltInRegistries.REGISTRY.get(this.resource.location());
    }
}
