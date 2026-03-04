package net.firemuffin303.muffinsmcapi.impl.dripstone.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;

public record FluidChanceInfo(Fluid fluid,float chance) {
    public static final Codec<FluidChanceInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.FLUID.byNameCodec().fieldOf("fluid").forGetter(FluidChanceInfo::fluid),
            Codec.floatRange(0,1).fieldOf("chance").forGetter(FluidChanceInfo::chance)
    ).apply(instance,FluidChanceInfo::new));
}
