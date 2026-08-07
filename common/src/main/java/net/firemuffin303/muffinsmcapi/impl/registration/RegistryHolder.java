package net.firemuffin303.muffinsmcapi.impl.registration;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RegistryHolder<T> implements Holder<T>, Supplier<T> {
    private Holder.Reference<T> holderObject;
    protected final ResourceKey<T> key;

    public RegistryHolder(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation resourceLocation){
        this.key = ResourceKey.create(registryKey,resourceLocation);
        createHolder(false);
    }

    public T get(){
        return this.value();
    }

    public ResourceLocation getResourceLocation() {
        return this.key.location();
    }


    @SuppressWarnings("unchecked")
    public void createHolder(boolean shouldThrow){
        if(this.holderObject != null) {
            return;
        }

        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(this.key.registry());
        if(registry != null){
            this.holderObject = registry.getHolder(this.key).orElse(null);
        }else if(shouldThrow){
            throw new IllegalStateException("Registry not present for " + this + ": " + this.key.registry());
        }
    }

    @Override
    public T value() {
        createHolder(true);
        if (this.holderObject == null) {
            throw new NullPointerException("Trying to access unbound value: " + this.key);
        }

        return holderObject.value();
    }

    @Override
    public boolean isBound() {
        createHolder(false);
        return this.holderObject != null && this.holderObject.isBound();
    }

    @Override
    public boolean is(ResourceLocation resourceLocation) {
        return resourceLocation.equals(this.key.location());
    }

    @Override
    public boolean is(ResourceKey<T> resourceKey) {
        return resourceKey == this.key;
    }

    @Override
    public boolean is(Predicate<ResourceKey<T>> predicate) {
        return predicate.test(this.key);
    }

    @Override
    public boolean is(TagKey<T> tagKey) {
        createHolder(false);
        return this.holderObject != null && this.holderObject.is(tagKey);
    }

    @Override
    public boolean is(Holder<T> holder) {
        createHolder(false);
        return this.holderObject != null && this.holderObject.is(holder);
    }

    @Override
    public Stream<TagKey<T>> tags() {
        createHolder(false);
        return this.holderObject != null ? this.holderObject.tags() : Stream.empty();
    }

    @Override
    public Either<ResourceKey<T>, T> unwrap() {
        return Either.left(this.key);
    }

    @Override
    public Optional<ResourceKey<T>> unwrapKey() {
        return Optional.of(this.key);
    }

    @Override
    public Kind kind() {
        return Kind.REFERENCE;
    }

    @Override
    public boolean canSerializeIn(HolderOwner<T> holderOwner) {
        createHolder(false);
        return this.holderObject != null && this.holderObject.canSerializeIn(holderOwner);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj instanceof Holder<?> h){
            return h.kind() == Kind.REFERENCE && h.unwrapKey().orElse(null) == this.key;

        }

        return false;
    }

    public Holder<T> getHolderObject() {
        createHolder(false);
        return this.holderObject != null ? this.holderObject : this;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
}
