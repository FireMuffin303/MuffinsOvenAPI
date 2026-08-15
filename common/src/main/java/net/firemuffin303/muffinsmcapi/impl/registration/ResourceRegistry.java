package net.firemuffin303.muffinsmcapi.impl.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResourceRegistry<T> {
    private final ResourceKey<Registry<T>> resource;
    private final String modId;
    private final Map<RegistryHolder<T>,Supplier<T>> values = new LinkedHashMap<>();

    private ResourceRegistry(ResourceKey<Registry<T>> resourceKey,String modId){
        this.resource = resourceKey;
        this.modId = modId;
    }

    public static <T> ResourceRegistry<T> create(ResourceKey<Registry<T>> resourceKey, String modId){
        return new ResourceRegistry<>(resourceKey,modId);
    }

    public Supplier<T> register(String id,Supplier<T> object){
        return this.register(ResourceLocation.fromNamespaceAndPath(this.modId,id),object);
    }

    public Supplier<T> register(ResourceLocation resourceLocation, Supplier<T> object){
        return registerHolder(resourceLocation,key -> object.get());
    }

    public Holder<T> registerHolder(String id,Supplier<T> object ){
        return registerHolder(ResourceLocation.fromNamespaceAndPath(this.modId,id), key -> object.get());
    }

    public RegistryHolder<T> registerHolder(ResourceLocation resourceLocation, Function<ResourceLocation,T> function){
        RegistryHolder<T> registryHolder = new RegistryHolder<>(this.resource,resourceLocation);
        this.values.putIfAbsent(registryHolder,() -> function.apply(resourceLocation));
        return registryHolder;
    }


    public ResourceKey<Registry<T>> getResource() {
        return resource;
    }

    public Map<RegistryHolder<T>,Supplier<T>> getValues() {
        return values;
    }

    public void init() {
        OvenRegistration.registerRegistry(this);
    }


}
