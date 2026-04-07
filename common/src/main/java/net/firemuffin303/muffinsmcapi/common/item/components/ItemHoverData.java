package net.firemuffin303.muffinsmcapi.common.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ItemHoverData(float friction) {
    public static Codec<ItemHoverData> CODEC = RecordCodecBuilder.create(itemHoverDataInstance -> itemHoverDataInstance.group(
            Codec.FLOAT.fieldOf("friction").forGetter(ItemHoverData::friction)
    ).apply(itemHoverDataInstance,ItemHoverData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf,ItemHoverData> DIRECT_STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT,ItemHoverData::friction,ItemHoverData::new);


    public float applyFriction(float value){
        return value * friction;
    }
}
