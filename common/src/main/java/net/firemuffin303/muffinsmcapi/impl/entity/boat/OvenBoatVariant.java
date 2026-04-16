package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record OvenBoatVariant(boolean raft) {
    public static final Codec<OvenBoatVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("raft").forGetter(ovenBoatVariant -> ovenBoatVariant.raft)
    ).apply(instance,OvenBoatVariant::new));


}
