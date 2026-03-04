package net.firemuffin303.muffinsmcapi.impl.dripstone.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

public record BlockFluidInfo(BlockState blockState, Fluid fluid) {
    public static final Codec<BlockFluidInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("block_state").forGetter(BlockFluidInfo::blockState),
            BuiltInRegistries.FLUID.byNameCodec().fieldOf("fluid").forGetter(BlockFluidInfo::fluid)
    ).apply(instance,BlockFluidInfo::new));
}
