package net.firemuffin303.muffinsmcapi.mixin.dripstone;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsmcapi.api.dripstone.DripstoneFluidRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin {

    @Inject(method = "maybeTransferFluid",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/PointedDripstoneBlock;findFillableCauldronBelowStalactiteTip(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;)Lnet/minecraft/core/BlockPos;"))
    private static void muffins$runSourceStateEvent(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, float f, CallbackInfo ci,
                                                    @Local Optional<PointedDripstoneBlock.FluidInfo> fluidInfo,@Local Fluid fluid,@Local(ordinal = 1) BlockPos tipPos
                                                    ){
        DripstoneFluidRegistry.SOURCE_STATE_EVENT.forEach(event -> event.onSourceStateChange(serverLevel,tipPos,fluidInfo.get().pos(),fluidInfo.get().sourceState(), fluid));
    }


    @ModifyReturnValue(method = "method_33279",at = @At("RETURN"))
    private static PointedDripstoneBlock.FluidInfo muffins$getAPIFluidInfo(PointedDripstoneBlock.FluidInfo original, @Local(ordinal = 1) BlockPos blockPos, @Local BlockState blockState){
        if(DripstoneFluidRegistry.hasFluidInfo(blockState)){
            Fluid fluid = DripstoneFluidRegistry.getFluid(blockState);
            return new PointedDripstoneBlock.FluidInfo(blockPos,fluid,blockState);
        }
        return original;
    }
}
