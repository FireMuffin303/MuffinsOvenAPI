package net.firemuffin303.muffinsmcapi.impl.dripstone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

public record DripstoneFluidInfo(BlockPos blockPos, Fluid fluid, BlockState blockState) {



}
