package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record OvenBoatVariant(ResourceLocation texture,boolean raft) {
    public static final Codec<OvenBoatVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(variant -> variant.texture),
            Codec.BOOL.fieldOf("raft").forGetter(ovenBoatVariant -> ovenBoatVariant.raft)
    ).apply(instance,OvenBoatVariant::new));


}
