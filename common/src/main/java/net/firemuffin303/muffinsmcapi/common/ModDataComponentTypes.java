package net.firemuffin303.muffinsmcapi.common;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.muffinsmcapi.impl.registration.RegistryHolder;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ResourceRegistry<DataComponentType<?>> DATA_COMPONENTS = ResourceRegistry.create(Registries.DATA_COMPONENT_TYPE, MuffinsMcAPI.MOD_ID);

    public static final Supplier<DataComponentType<ResourceLocation>> OVEN_BOAT_TYPE = registerDataComponent("oven_boat_type",
            builder -> builder.persistent(ResourceLocation.CODEC).networkSynchronized(ResourceLocation.STREAM_CODEC));


    @SuppressWarnings("unchecked")
    public static <T> Supplier<DataComponentType<T>> registerDataComponent(String id,UnaryOperator<DataComponentType.Builder<T>> builder){
        return (Supplier<DataComponentType<T>>) (Object) DATA_COMPONENTS.register(id,() -> builder.apply(DataComponentType.builder()).build());
    }
}
